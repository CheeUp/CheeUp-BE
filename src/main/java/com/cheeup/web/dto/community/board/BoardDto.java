package com.cheeup.web.dto.community.board;

import com.cheeup.domain.enums.BoardCategory;
import lombok.Builder;

public record BoardDto() {
    public record RequestDto(String name, Boolean isAnonymous, BoardCategory category) {
    }

    @Builder
    public record ResponseDto(Long id, String name, Boolean isAnonymous, BoardCategory category) {
    }
}
