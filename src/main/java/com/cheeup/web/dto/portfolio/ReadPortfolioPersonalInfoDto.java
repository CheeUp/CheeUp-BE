package com.cheeup.web.dto.portfolio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReadPortfolioPersonalInfoDto() {

    public record ResponseDto(
            @NotNull
            Long id,

            @NotNull
            PersonalInfoDto personalInfo
    ) {
    }

    public record PersonalInfoDto(
            @NotNull
            Long id,

            @NotBlank
            String name,

            @NotBlank
            @Email
            String email,

            @NotBlank
            String introduction,

            String githubUrl,

            String blogUrl
    ) {
    }
}
