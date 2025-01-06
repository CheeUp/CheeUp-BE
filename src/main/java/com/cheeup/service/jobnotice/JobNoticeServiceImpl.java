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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobNoticeServiceImpl implements JobNoticeService {
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final JobNoticeRepository jobNoticeRepository;
    private final JobNoticeMapper jobNoticeMapper;
    private final JobDescriptionMapper jobDescriptionMapper;


    @Override
    @Transactional
    public void createJobNotice(PostJobNoticeDto.RequestDto requestDto) {
        // 채용공고 테이블 엔티티 생성
        JobNotice jobNotice = jobNoticeMapper.toEntity(requestDto);

        List<JobNoticeJob> jobNoticeJobList = requestDto.jobIds().stream()
                .map(jobId -> {
                    Job job = jobRepository.findById(jobId)
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

                    List<JobDescriptionSkill> jobDescriptionSkillList = jobDescriptionDto.skillIds().stream()
                            .map(skillId -> {
                                Skill skill = skillRepository.findById(skillId)
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
