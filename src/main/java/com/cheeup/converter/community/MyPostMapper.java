package com.cheeup.converter.community;

import com.cheeup.domain.community.Post;
import com.cheeup.domain.member.Member;
import com.cheeup.web.dto.common.Pagination;
import com.cheeup.web.dto.community.ReadMyPostsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyPostMapper {

    @Mapping(source = "post.id", target = "postId")
    ReadMyPostsDto.PostResponse toPostDto(Post post, long boardId, int commentCount, ReadMyPostsDto.AuthorResponse author);

    ReadMyPostsDto.Response toDto(List<ReadMyPostsDto.PostResponse> posts, Pagination pagination);
}
