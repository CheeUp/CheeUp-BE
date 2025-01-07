package com.cheeup.web.dto.portfolio;

import com.cheeup.domain.enums.SkillLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ReadPortfolioTechSkillsDto() {

    public record ResponseDto(
            @NotNull
            Long id,

            @NotNull
            List<TechSkillDto> techSkills
    ) {
    }

    public record TechSkillDto(
            @NotNull
            Long id,

            @NotBlank
            String name,

            @NotNull
            SkillLevel level,

            String description
    ) {
    }
}
