package com.cheeup.web.dto.community;

import com.cheeup.web.dto.member.ReadMemberDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record ReadPostDto() {

    // 단일 게시글 조회를 위한 Dto
    // 단일 게시글의 경우 Comment 정보가 추가적으로 필요하다
    @Builder
    public record DetailResponseDto(
            Long id,
            BoardDto.ResponseDto board,
            ReadMemberDto.ResponseDto member,
            String title,
            String content,
            Integer likes,
            Integer scraps, // 일단 사용하지 않는다고 했지만 포함헀음
            Integer hits,
            Boolean isAnonymous,
            LocalDateTime createdAt,

//            List<PostFile> files, //추후 구현
            List<ReadCommentDto.ResponseDto> commentList
    ) {
    }

    // 게시글 목록 조회를 위한 Dto
    // 게시글 목록의 경우 Comment 정보가 필요없다.
    @Builder
    public record ListResponseDto(
            Long id,
            ReadMemberDto.ResponseDto member,
            String title,
            String content,
            Integer likes,
            Integer scraps, // 일단 사용하지 않는다고 했지만 포함헀음
            Integer hits,
            Boolean isAnonymous,
            LocalDateTime createdAt,
            Integer commentCount
    ) {
    }
}
