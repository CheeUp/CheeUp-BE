package com.cheeup.web.dto.jobnotice;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record PostJobNoticeDto() {
    public record RequestDto(
            @NotBlank
            @Size(max = 30, message = "공고 제목은 30자 미만으로 작성해야 합니다.")
            String title,

            @NotEmpty
            List<JobNoticeDto.PositionDto> positions,

            @NotBlank
            @Size(max = 30, message = "회사 이름은 30자 미만으로 작성해야 합니다.")
            String company,

            @Positive
            Integer companySize,

            @NotBlank
            @Size(max = 255, message = "링크는 255자 미만으로 작성해야 합니다.")
            String url,

            @NotNull
            LocalDate startDate,

            @NotNull
            LocalDate endDate,

            List<JobNoticeDto.ImageDto> images,

            List<JobNoticeDto.JobDescriptionDto> jobDescriptions

    ) {

        @AssertTrue(message = "endDate는 startDate보다 빠를 수 없습니다.")
        public boolean isValidDateRange() {
            return !endDate.isBefore(startDate);
        }
    }

}
