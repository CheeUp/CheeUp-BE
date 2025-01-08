package com.cheeup.web.dto.community;

import com.cheeup.web.dto.common.Pagination;
import lombok.Builder;

import java.util.List;

public class ReadMyPostsDto {

    @Builder
    public record ResponseDto(
        Pagination pagination,
        List<MyPostDto.PostResponse> posts
    ) {
    }

}
