package com.cheeup.web.dto.jobnotice;

import com.cheeup.domain.enums.CompanySize;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record PostJobNoticeDto() {
    public record RequestDto(
            @NotBlank(message = "공고 제목이 비어 있습니다.")
            @Size(max = 30, message = "공고 제목은 30자 미만으로 작성해야 합니다.")
            String title,

            @NotBlank(message = "회사 이름이 비어 있습니다.")
            @Size(max = 30, message = "회사 이름은 30자 미만으로 작성해야 합니다.")
            String company,

            @NotNull(message = "회사 규모가 비어 있습니다.")
            CompanySize companySize,

            @NotBlank(message = "공고 링크가 비어 있습니다.")
            @Size(max = 255, message = "링크는 255자 미만으로 작성해야 합니다.")
            String url,

            @NotNull(message = "시작 날짜가 비어 있습니다.")
            LocalDate startDate,

            @NotNull(message = "종료 날짜가 비어 있습니다.")
            LocalDate endDate,

            @NotEmpty(message = "채용 포지션이 비어 있습니다.")
            List<Long> jobs,

            List<JobNoticeDto.ImageDto> images,

            List<JobNoticeDto.JobDescriptionDto> jobDescriptions

    ) {

        @AssertTrue(message = "endDate는 startDate보다 빠를 수 없습니다.")
        public boolean isValidDateRange() {
            return !endDate.isBefore(startDate);
        }
    }

}
