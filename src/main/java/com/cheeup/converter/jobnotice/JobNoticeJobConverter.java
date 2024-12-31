package com.cheeup.converter.jobnotice;

import com.cheeup.domain.common.Job;
import com.cheeup.domain.jobnotice.JobNoticeJob;
import java.util.List;
import java.util.stream.Collectors;

public class JobNoticeJobConverter {

    public static List<JobNoticeJob> toJobNoticeJob(List<Job> newJobList) {
        return newJobList.stream()
                .map(job -> JobNoticeJob.builder()
                        .job(job)
                        .build())
                .collect(Collectors.toList());
    }
}
