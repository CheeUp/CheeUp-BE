package com.cheeup.web.dto.job;

import jakarta.validation.constraints.Size;
import java.util.List;

public record JobNoticeDto() {

    public record PositionDto(
            Long id,
            String name) {
    }

    public record ImageDto(
            String name,
            Integer size,
            String type,
            String url
    ) {
    }


    public record JobDescriptionDto(
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
            Long id,
            String name
    ) {
    }


}
