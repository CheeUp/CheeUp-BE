package com.cheeup.converter.job;

import com.cheeup.domain.job.JobNotice;
import com.cheeup.web.dto.job.JoinJobNoticeDto;
import java.util.ArrayList;

public class JobNoticeConverter {

    public static JobNotice toJobNotice(JoinJobNoticeDto.RequestDto joinDto) {
        return JobNotice.builder()
                .title(joinDto.title())
                .company(joinDto.company())
                .companySize(joinDto.companySize())
                .url(joinDto.url())
                .startDate(joinDto.startDate())
                .endDate(joinDto.endDate())
                .jobDescriptionList(new ArrayList<>())
                .jobNoticeJobList(new ArrayList<>())
                .jobNoticeFileList(new ArrayList<>())
                .build();

    }
}
