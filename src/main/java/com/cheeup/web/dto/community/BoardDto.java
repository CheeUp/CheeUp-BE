package com.cheeup.web.dto.community;

import com.cheeup.domain.enums.BoardCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record BoardDto() {

    public record RequestDto(
            @NotNull(message = "이름은 필수 입력 항목입니다.")
            @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하로 입력해야 합니다.")
            String name,
            @NotNull(message = "익명 여부는 필수 입력 항목입니다.")
            Boolean isAnonymous,
            @NotNull(message = "카테고리는 필수 입력 항목입니다.")
            BoardCategory category) {
    }

    @Builder
    public record ResponseDto(Long id, String name, Boolean isAnonymous, BoardCategory category) {
    }
}
