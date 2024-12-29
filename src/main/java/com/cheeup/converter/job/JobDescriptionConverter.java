package com.cheeup.converter.job;

import com.cheeup.domain.enums.JobDescriptionType;
import com.cheeup.domain.job.JobDescription;
import com.cheeup.web.dto.job.JobNoticeDto;
import java.util.ArrayList;

public class JobDescriptionConverter {
    public static JobDescription toJobDescription(JobNoticeDto.JobDescriptionDto jobDescriptionDTO) {
        JobDescriptionType jobDescriptionType = null;
        switch (jobDescriptionDTO.type()) {
            case "신입":
                jobDescriptionType = JobDescriptionType.신입;
                break;
            case "경력":
                jobDescriptionType = JobDescriptionType.경력;
                break;
        }
        return JobDescription.builder()
                .title(jobDescriptionDTO.title())
                .type(jobDescriptionType)
                .location(jobDescriptionDTO.location())
                .description(jobDescriptionDTO.description())
                .requirement(jobDescriptionDTO.requirement())
                .preferredRequirement(jobDescriptionDTO.preferredRequirement())
                .jobDescriptionSkills(new ArrayList<>())
                .build();
    }
}
