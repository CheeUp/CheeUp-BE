package com.cheeup.web.dto.portfolio;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public record ReadPortfolioDto() {

    public record ResponseDto(
            @NotNull
            Long id,

            @NotNull
            Long memberId,

            @NotBlank
            @Size(max = 100, message = "사용자 이름은 100자 미만으로 작성해야 합니다.")
            String memberName,

            @NotBlank
            @Size(max = 50, message = "직무 이름은 50자 미만으로 작성해야 합니다.")
            String jobName,

            @NotBlank
            @Size(max = 200, message = "포트폴리오 제목은 200자 미만으로 작성해야 합니다.")
            String title,

            @NotNull
            Boolean open,

            @NotNull
            LocalDateTime createdAt,

            @NotNull
            LocalDateTime updatedAt,

            @Valid
            List<SkillDto> skills
    ) {
    }

    public record SkillDto(
            @NotNull
            Long id,

            @NotBlank
            @Size(max = 50, message = "스킬 이름은 50자 미만으로 작성해야 합니다.")
            String name
    ) {
    }
}
