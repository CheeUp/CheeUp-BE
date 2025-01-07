package com.cheeup.converter.community;

import com.cheeup.domain.community.Post;
import com.cheeup.web.dto.community.CreatePostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(CreatePostDto.RequestDto requestDto);
}
