package com.cheeup.web.dto.job;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record JoinJobNoticeDto() {
    public record RequestDto(
            @Size(max = 30, message = "공고 제목은 30자 미만으로 작성해야 합니다.")
            String title,
            List<JobNoticeDto.PositionDto> positions,
            @Size(max = 30, message = "회사 이름은 30자 미만으로 작성해야 합니다.")
            String company,
            Integer companySize,
            @Size(max = 255, message = "링크는 255자 미만으로 작성해야 합니다.")
            String url,
            LocalDate startDate,
            LocalDate endDate,
            List<JobNoticeDto.ImageDto> images,
            List<JobNoticeDto.JobDescriptionDto> jobDescriptions
    ) {
    }

    public record ResponseDto(String result) {
    }


}
