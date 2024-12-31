package com.cheeup.web.dto.jobnotice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;

public record JobNoticeDto() {

    public record JobDto(
            @NotNull
            @Positive
            Long id,

            @NotBlank

            String name) {
    }

    public record ImageDto(
            @NotBlank
            String name,

            @NotNull
            @PositiveOrZero
            Integer size,

            @NotBlank
            String type,

            @NotBlank
            String url
    ) {
    }


    public record JobDescriptionDto(
            @NotBlank
            @Size(max = 100, message = "직무 이름은 100자 미만으로 작성해야 합니다.")
            String title,
            String type,
            @Size(max = 50, message = "지역 이름은 50자 미만으로 작성해야 합니다.")
            String location,
            String description,
            String requirement,
            String preferredRequirement,
            List<SkillDto> skills
    ) {
    }

    public record SkillDto(
            @NotNull
            Long id,

            @NotBlank()
            @Size()
            String name
    ) {
    }


}
