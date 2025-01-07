package com.cheeup.web.dto.portfolio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePortfolioDto() {
    public record RequestDto(
            @NotBlank
            @Size(min = 1, max = 50)
            String title,

            @NotNull
            Long jobId,

            @NotNull
            Boolean open
    ) {
    }
}
