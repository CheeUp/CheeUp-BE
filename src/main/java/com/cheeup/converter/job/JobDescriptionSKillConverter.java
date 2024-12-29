package com.cheeup.converter.job;

import com.cheeup.domain.common.Skill;
import com.cheeup.domain.job.JobDescriptionSkill;
import java.util.List;
import java.util.stream.Collectors;

public class JobDescriptionSKillConverter {
    public static List<JobDescriptionSkill> toJobDescriptionSkill(List<Skill> newSkills) {
        return newSkills.stream()
                .map(skill -> JobDescriptionSkill.builder()
                        .skill(skill)
                        .build())
                .collect(Collectors.toList());
    }
};
