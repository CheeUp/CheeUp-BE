package com.cheeup.converter.community;

import com.cheeup.domain.community.Post;
import com.cheeup.web.dto.common.Pagination;
import com.cheeup.web.dto.community.MyPostDto;
import com.cheeup.web.dto.community.ReadMyPostsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyPostMapper {

    @Mapping(source = "post.id", target = "postId")
    MyPostDto.PostResponse toPostDto(Post post, long boardId, int commentCount, MyPostDto.AuthorResponse author);

    ReadMyPostsDto.ResponseDto toDto(List<MyPostDto.PostResponse> posts, Pagination pagination);
}
