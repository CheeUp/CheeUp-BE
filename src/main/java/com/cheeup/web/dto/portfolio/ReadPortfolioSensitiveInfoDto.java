package com.cheeup.web.dto.portfolio;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record ReadPortfolioSensitiveInfoDto() {

    public record ResponseDto(
            @NotNull
            Long id,
            @NotNull
            SensitiveInfoDto sensitiveInfo
    ) {
    }

    @Builder
    public record SensitiveInfoDto(
            MilitaryDto military,
            VeteranDto veteran,
            DisabilityDto disability
    ) {
    }

    @Builder
    public record MilitaryDto(
            Long id,
            String type,
            String branch,
            String specialty,
            String rank,
            String discharge,
            String startDate,
            String endDate
    ) {
    }

    @Builder
    public record VeteranDto(
            Long id,
            String status,
            String relation,
            String number,
            Double ratio
    ) {
    }

    @Builder
    public record DisabilityDto(
            Long id,
            String status,
            String type,
            String grade
    ) {
    }
}
