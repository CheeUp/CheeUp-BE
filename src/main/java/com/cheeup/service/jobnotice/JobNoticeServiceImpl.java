package com.cheeup.service.jobnotice;

import com.cheeup.apiPayload.code.error.codes.JobErrorCode;
import com.cheeup.apiPayload.exception.handler.JobException;
import com.cheeup.converter.jobnotice.JobDescriptionMapper;
import com.cheeup.converter.jobnotice.JobNoticeMapper;
import com.cheeup.domain.common.Job;
import com.cheeup.domain.common.Skill;
import com.cheeup.domain.jobnotice.JobDescription;
import com.cheeup.domain.jobnotice.JobDescriptionSkill;
import com.cheeup.domain.jobnotice.JobNotice;
import com.cheeup.domain.jobnotice.JobNoticeJob;
import com.cheeup.repository.common.JobRepository;
import com.cheeup.repository.common.SkillRepository;
import com.cheeup.repository.jobnotice.JobNoticeRepository;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobNoticeServiceImpl implements JobNoticeService {
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final JobNoticeRepository jobNoticeRepository;
    private final JobNoticeMapper jobNoticeMapper = Mappers.getMapper(JobNoticeMapper.class);
    private final JobDescriptionMapper jobDescriptionMapper = Mappers.getMapper(JobDescriptionMapper.class);


    @Override
    @Transactional
    public void createJobNotice(PostJobNoticeDto.RequestDto requestDto) {
        // 채용공고 테이블 엔티티 생성
        JobNotice jobNotice = jobNoticeMapper.toEntity(requestDto);

        // 채용공고 직무 설정 및 직무 이름으로 직무 조회 후 직무에 채용공고 설정
        List<JobNoticeJob> jobNoticeJobList = requestDto.jobList().stream()
                .map(jobDto -> {
                    Job job = jobRepository.findById(jobDto.id())
                            .orElseThrow(() -> new JobException(JobErrorCode.JOB_NOT_FOUND));
                    return JobNoticeJob.builder()
                            .job(job)
                            .jobNotice(jobNotice)
                            .build();
                }).toList();
        // jobNotice.setJobNoticeJobList(jobNoticeJobList);

        List<JobDescription> jobDescriptionList = requestDto.jobDescriptionList().stream()
                .map(jobDescriptionDto -> {
                    JobDescription jobDescription = jobDescriptionMapper.toEntity(jobDescriptionDto);
                    jobDescription.setJobNotice(jobNotice);

                    List<JobDescriptionSkill> jobDescriptionSkills = jobDescriptionDto.skills().stream()
                            .map(skillDto -> {
                                Skill skill = skillRepository.findById(skillDto.id()).orElseThrow();
                                return JobDescriptionSkill.builder()
                                        .skill(skill)
                                        .jobDescription(jobDescription)
                                        .build();
                            }).toList();

                    //jobDescription.setJobDescriptionSkills(jobDescriptionSkills);
                    return jobDescription;
                }).toList();

        // jobNotice.setJobDescriptionList(jobDescriptionList)

        jobNoticeRepository.save(jobNotice);
    }
}
