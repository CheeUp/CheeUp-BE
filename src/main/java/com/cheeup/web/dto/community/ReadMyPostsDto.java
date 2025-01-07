package com.cheeup.web.dto.community;

import com.cheeup.web.dto.common.Pagination;
import lombok.Builder;

import java.util.List;

public class ReadMyPostsDto {

    @Builder
    public record Response(
        Pagination pagination,
        List<PostResponse> posts
    ) {
    }

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
