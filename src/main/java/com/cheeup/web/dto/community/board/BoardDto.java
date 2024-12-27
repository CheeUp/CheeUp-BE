package com.cheeup.web.dto.community.board;

import com.cheeup.domain.enums.BoardCategory;
import lombok.Builder;
import lombok.Getter;

public class BoardDto {
    @Getter
    public static class Request {
        private String name;
        private Boolean isAnonymous;
        private BoardCategory category;
    }

    @Builder
    public static class Response {
        private Long id;
        private String name;
        private Boolean isAnonymous;
        private BoardCategory category;
    }
}
