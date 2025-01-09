package com.cheeup.web.dto.jobnotice;

import java.time.LocalDate;

public class ScrappedJobNoticeDto {

    public record JobNotice(
            Long id,
            String title,
            String companyName,
            LocalDate startDate,
            LocalDate endDate,
            String jobNoticeLink
    ){ }
}
