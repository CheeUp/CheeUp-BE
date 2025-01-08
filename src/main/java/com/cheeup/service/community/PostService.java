package com.cheeup.service.community;

import com.cheeup.web.dto.community.CreatePostDto;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    // 게시글 상세 조회
    void getPost();

    // 게시글 목록 조회
    void getPostList();

    // 게시글 작성
    void createPost(CreatePostDto.RequestDto requestDto);

    // 게시글 수정
    void updatePost();

    // 게시글 삭제
    void deletePost();

}
