package com.cheeup.web.dto.jobnotice;

import com.cheeup.validation.annotation.ExistJobDescriptionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;

public record JobNoticeDto() {

    public record ImageDto(
            @NotBlank(message = "이미지 이름이 비어 있습니다.")
            String name,

            @NotNull(message = "이미지 크기가 비어 있습니다.")
            @PositiveOrZero(message = "이미지 크기가 음수입니다.")
            Integer size,

            @NotBlank(message = "파일 유형이 비어 있습니다.")
            String type,

            @NotBlank(message = "이미지 링크가 비어 있습니다.")
            String url
    ) {
    }


    public record JobDescriptionDto(
            @NotBlank(message = "직무 이름이 비어 있습니다.")
            @Size(max = 100, message = "직무 이름은 100자 미만으로 작성해야 합니다.")
            String title,
            @ExistJobDescriptionType
            String type,
            @Size(max = 50, message = "지역 이름은 50자 미만으로 작성해야 합니다.")
            String location,
            String description,
            String requirement,
            String preferredRequirement,
            @NotEmpty(message = "기술 스택이 비어 있습니다.")
            List<Long> skills
    ) {
    }
}
