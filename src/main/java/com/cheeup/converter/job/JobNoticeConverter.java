package com.cheeup.converter.job;

import com.cheeup.domain.job.JobNotice;
import com.cheeup.web.dto.job.PostJobNoticeDto;
import com.cheeup.web.dto.job.PostJobNoticeDto.ResponseDto;
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

    public static ResponseDto toResponseDto() {
        return new PostJobNoticeDto.ResponseDto("취업 공고 등록에 성공했습니다");
    }
}
