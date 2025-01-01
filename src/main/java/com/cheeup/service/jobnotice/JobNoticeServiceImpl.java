package com.cheeup.service.jobnotice;

import com.cheeup.apiPayload.code.error.codes.JobErrorCode;
import com.cheeup.apiPayload.code.error.codes.SkillErrorCode;
import com.cheeup.apiPayload.exception.handler.JobException;
import com.cheeup.apiPayload.exception.handler.SkillException;
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

        List<JobNoticeJob> jobNoticeJobList = requestDto.jobs().stream()
                .map(jobDto -> {
                    Job job = jobRepository.findByIdAndName(jobDto.id(), jobDto.name())
                            .orElseThrow(() -> new JobException(
                                    JobErrorCode.JOB_NOT_FOUND));

                    JobNoticeJob jobNoticeJob = JobNoticeJob.builder().build();
                    jobNoticeJob.setJobNotice(jobNotice);
                    jobNoticeJob.setJob(job);

                    return jobNoticeJob;
                }).toList();

        List<JobDescription> jobDescriptionList = requestDto.jobDescriptions().stream()
                .map(jobDescriptionDto -> {
                    JobDescription jobDescription = jobDescriptionMapper.toEntity(jobDescriptionDto);

                    List<JobDescriptionSkill> jobDescriptionSkillList = jobDescriptionDto.skills().stream()
                            .map(skillDto -> {
                                Skill skill = skillRepository.findById(skillDto.id())
                                        .orElseThrow(() -> new SkillException(SkillErrorCode.SKILL_NOT_FOUND));

                                JobDescriptionSkill jobDescriptionSkill = JobDescriptionSkill.builder().build();
                                jobDescriptionSkill.setJobDescription(jobDescription);
                                jobDescriptionSkill.setSkill(skill);

                                return jobDescriptionSkill;
                            }).toList();

                    jobDescription.setJobNotice(jobNotice);

                    return jobDescription;
                }).toList();

        jobNoticeRepository.save(jobNotice);
    }
}
