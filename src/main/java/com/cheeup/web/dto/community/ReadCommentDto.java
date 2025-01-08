package com.cheeup.web.dto.community;

import com.cheeup.web.dto.member.ReadMemberDto;
import java.time.LocalDateTime;
import java.util.List;

public record ReadCommentDto() {
    public record ResponseDto(
//            Long commentId,
            Long id,
            ReadMemberDto.ResponseDto member,
            ReadCommentDto.ResponseDto parentComment,
            String content,
            Integer likes,
            LocalDateTime createdAt,
            List<ReadCommentDto.ResponseDto> childComments
    ) {
    }
}
