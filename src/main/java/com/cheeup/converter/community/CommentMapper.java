package com.cheeup.converter.community;

import com.cheeup.domain.community.Comment;
import com.cheeup.web.dto.community.ReadCommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    //    @Mapping(source = "id", target = "commentId")
    ReadCommentDto.ResponseDto toCommentDto(Comment comment);

}
