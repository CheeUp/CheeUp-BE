package com.cheeup.converter.community;

import com.cheeup.domain.community.Post;
import com.cheeup.web.dto.community.CreatePostDto;
import com.cheeup.web.dto.community.ReadPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(CreatePostDto.RequestDto requestDto);

    @Mapping(source = "commentList", target = "commentList")
    ReadPostDto.DetailResponseDto toDetailResponseDto(Post post);
}
