package com.cheeup.service.job;

import com.cheeup.apiPayload.code.status.ErrorStatus;
import com.cheeup.apiPayload.exception.handler.CommonHandler;
import com.cheeup.apiPayload.exception.handler.JobNoticeHandler;
import com.cheeup.converter.job.JobNoticeConverter;
import com.cheeup.converter.job.JobNoticeJobConverter;
import com.cheeup.domain.common.Job;
import com.cheeup.domain.common.Skill;
import com.cheeup.domain.job.JobNotice;
import com.cheeup.domain.job.JobNoticeJob;
import com.cheeup.repository.common.SkillRepository;
import com.cheeup.repository.job.JobNoticeRepository;
import com.cheeup.repository.job.JobRepository;
import com.cheeup.web.dto.job.JobNoticeDto.SkillDto;
import com.cheeup.web.dto.job.JoinJobNoticeDto.RequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobServiceImpl implements JobService {
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final JobNoticeRepository jobNoticeRepository;

    @Override
    @Transactional
    public void join(RequestDto joinDto) {
        //TODO 관리자가 등록하는 건지 확인하는 로직

        //종료 날짜가 시작 날짜보다 뒤에 있도록
        if (!joinDto.startDate().isBefore(joinDto.endDate())) {
            throw new JobNoticeHandler(ErrorStatus._ENDDATE_IS_BEFORE_STARTDATE);
        }

        JobNotice newJobNotice = JobNoticeConverter.toJobNotice(joinDto);

        //데이터베이스에 있는 position인지 검증
        List<Job> newJobList = joinDto.positions().stream()
                .map(dto -> jobRepository.findById(dto.id())
                        .filter(job -> job.getName().equals(dto.name()))
                        .orElseThrow(() -> new CommonHandler(ErrorStatus._JOB_NOTFOUND)))
                .collect(Collectors.toList());

        List<JobNoticeJob> newJobNoticeJobList = JobNoticeJobConverter.toJobNoticeJob(newJobList);
        newJobNoticeJobList.forEach(jobNoticeJob -> jobNoticeJob.setJobNotice(newJobNotice));

        jobNoticeRepository.save(newJobNotice);

//        List<JobDescription> newJobDescriptionList = joinDto.jobDescriptions().stream()
//                .filter(dto -> { // 신입 경력 인턴 ENUM에 포함되는지 검사
//                    if (!JobDescriptionType.isValidType(dto.type())) {
//                        throw new JobNoticeHandler(ErrorStatus._JOB_DESCRIPTION_TYPE_NOTFOUND);
//                    }
//                    return true;
//                })
//                .map(JobDescriptionConverter::toJobDescription)
//                .collect(Collectors.toList());
//
//        // newJobDescriptionList에 skill 넣기

    }

    private void setJobDescriptionSkills(List<SkillDto> skillDTO) {

        List<Skill> skills = skillDTO.stream()
                .map(dto -> {
                    return skillRepository.findById(dto.id())
                            .orElseThrow(() -> new CommonHandler(ErrorStatus._SKILL_NOTFOUND));
                })
                .collect(Collectors.toList());

    }
}
