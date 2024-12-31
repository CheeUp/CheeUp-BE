package com.cheeup.converter.jobnotice;

import com.cheeup.domain.jobnotice.JobNotice;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import java.util.ArrayList;

public class JobNoticeConverter {

    public static JobNotice toJobNotice(PostJobNoticeDto.RequestDto joinDto) {
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
