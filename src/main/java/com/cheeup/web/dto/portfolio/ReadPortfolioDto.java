package com.cheeup.web.dto.portfolio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReadPortfolioDto() {

    public record ResponseDto(
            @NotNull
            Long id,

            @NotNull
            String job,

            @NotNull
            Boolean open
    ) {
    }

    public record SkillDto(
            @NotNull
            Long id,

            @NotBlank
            @Size(max = 50)
            String name
    ) {
    }
}
