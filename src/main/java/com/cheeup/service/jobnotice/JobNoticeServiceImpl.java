package com.cheeup.service.jobnotice;

import com.cheeup.apiPayload.code.status.ErrorStatus;
import com.cheeup.apiPayload.exception.handler.CommonHandler;
import com.cheeup.apiPayload.exception.handler.JobNoticeHandler;
import com.cheeup.converter.job.JobDescriptionConverter;
import com.cheeup.converter.job.JobDescriptionSKillConverter;
import com.cheeup.converter.job.JobNoticeConverter;
import com.cheeup.converter.job.JobNoticeJobConverter;
import com.cheeup.domain.common.Job;
import com.cheeup.domain.common.Skill;
import com.cheeup.domain.enums.JobDescriptionType;
import com.cheeup.domain.job.JobDescription;
import com.cheeup.domain.job.JobDescriptionSkill;
import com.cheeup.domain.job.JobNotice;
import com.cheeup.domain.job.JobNoticeJob;
import com.cheeup.domain.jobnotice.JobNotice;
import com.cheeup.repository.common.SkillRepository;
import com.cheeup.repository.job.JobNoticeRepository;
import com.cheeup.repository.job.JobRepository;
import com.cheeup.web.dto.job.PostJobNoticeDto.RequestDto;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobNoticeServiceImpl implements JobNoticeService {
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final JobNoticeRepository jobNoticeRepository;

    @Override
    @Transactional
    public void createJoinNotice(PostJobNoticeDto.RequestDto requestDto) {
        //TODO 관리자가 등록하는 건지 확인하는 로직

        JobNotice newJobNotice = JobNoticeConverter.toJobNotice(joinDto);

        //데이터베이스에 있는 position인지 검증
        List<Job> newJobList = joinDto.positions().stream()
                .map(dto -> jobRepository.findByName(dto.name())
                        .orElseThrow(() -> new CommonHandler(ErrorStatus._JOB_NOTFOUND)))
                .collect(Collectors.toList());

        List<JobNoticeJob> newJobNoticeJobList = JobNoticeJobConverter.toJobNoticeJob(newJobList);
        newJobNoticeJobList.forEach(jobNoticeJob -> jobNoticeJob.setJobNotice(newJobNotice));

        List<JobDescription> newJobDescriptionList = joinDto.jobDescriptions().stream()
                .filter(dto -> { // 신입 경력 인턴 ENUM에 포함되는지 검사
                    if (!JobDescriptionType.isValidType(dto.type())) {
                        throw new JobNoticeHandler(ErrorStatus._JOB_DESCRIPTION_TYPE_NOTFOUND);
                    }
                    return true;
                })
                .map(JobDescriptionConverter::toJobDescription)
                .collect(Collectors.toList());

//        for (int i = 0; i < newJobDescriptionList.size(); i++) {
//            List<Skill> newSkills = joinDto.jobDescriptions().get(i).skills().stream()
//                    .map(dto -> skillRepository.findById(dto.id())
//                            .filter(skill -> skill.getName().equals(dto.name()))
//                            .orElseThrow(() -> new CommonHandler(ErrorStatus._SKILL_NOTFOUND)))
//                    .collect(Collectors.toList());
//            List<JobDescriptionSkill> newJobDescriptionSkillList = JobDescriptionSKillConverter.toJobDescriptionSkill(
//                    newSkills);
//            int index = i;
//            newJobDescriptionSkillList.forEach(
//                    jobDescriptionSkill -> jobDescriptionSkill.setJobDescription(newJobDescriptionList.get(index)));
//        }
//

        List<JobDescriptionSkill> newJobDescriptionSkillList = IntStream.range(0, newJobDescriptionList.size())
                .mapToObj(i -> {
                    List<Skill> newSkills = joinDto.jobDescriptions().get(i).skills().stream()
                            .map(dto -> skillRepository.findByName(dto.name())
                                    .orElseThrow(() -> new CommonHandler(ErrorStatus._SKILL_NOTFOUND)))
                            .collect(Collectors.toList());

                    return JobDescriptionSKillConverter.toJobDescriptionSkill(newSkills).stream()
                            .peek(jobDescriptionSkill -> jobDescriptionSkill.setJobDescription(
                                    newJobDescriptionList.get(i)))
                            .collect(Collectors.toList());
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        for (int i = 0; i < newJobDescriptionList.size(); i++) {
            newJobDescriptionList.get(i).setJobNotice(newJobNotice);
        }

        // newJobDescriptionList에 skill 넣기
        jobNoticeRepository.save(newJobNotice);
    }

}
