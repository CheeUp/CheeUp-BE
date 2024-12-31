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
            @NotBlank
            @Size(max = 30, message = "공고 제목은 30자 미만으로 작성해야 합니다.")
            String title,

            @NotBlank
            @Size(max = 30, message = "회사 이름은 30자 미만으로 작성해야 합니다.")
            String company,

            @NotNull
            CompanySize companySize,

            @NotBlank
            @Size(max = 255, message = "링크는 255자 미만으로 작성해야 합니다.")
            String url,

            @NotNull
            LocalDate startDate,

            @NotNull
            LocalDate endDate,

            @NotEmpty
            List<JobNoticeDto.JobDto> jobList,

            List<JobNoticeDto.ImageDto> imageList,

            List<JobNoticeDto.JobDescriptionDto> jobDescriptionList

    ) {

        @AssertTrue(message = "endDate는 startDate보다 빠를 수 없습니다.")
        public boolean isValidDateRange() {
            return !endDate.isBefore(startDate);
        }
    }

}
