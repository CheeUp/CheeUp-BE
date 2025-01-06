package com.cheeup.web.dto.jobnotice;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record GetAllJobNoticesDto() {
    public record ResponseDto(
            @NotBlank
            String company,
            @NotNull
            LocalDate startDate,
            @NotNull
            LocalDate endDate,
            @NotNull
            Boolean isScrapped

    ) {

        @AssertTrue(message = "endDate는 startDate보다 빠를 수 없습니다.")
        public boolean isValidDateRange() {
            return !endDate.isBefore(startDate);
        }
    }

}
