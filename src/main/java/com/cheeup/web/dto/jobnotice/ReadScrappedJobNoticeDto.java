package com.cheeup.web.dto.jobnotice;

import com.cheeup.web.dto.common.Pagination;

import java.util.List;

public class ReadScrappedJobNoticeDto {

    public record ResponseDto(
            Pagination pagination,
            List<ScrappedJobNoticeDto.JobNotice> jobNotices
    ) {
    }
}
