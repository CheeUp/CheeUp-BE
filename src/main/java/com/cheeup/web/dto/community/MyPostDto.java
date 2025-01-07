package com.cheeup.web.dto.community;

import lombok.Builder;

public class MyPostDto {

    public record PostResponse (
            Long postId,
            Long boardId,
            String title,
            String content,
            String createdAt,
            int hits,
            int likes,
            int commentCount,
            AuthorResponse author
    ) {
    }

    @Builder
    public record AuthorResponse(
            Long memberId,
            String profileImageUrl,
            String nickname,
            int experience,
            String tier
    ) {
    }
}
