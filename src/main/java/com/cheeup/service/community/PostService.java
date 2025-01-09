package com.cheeup.service.community;

import com.cheeup.web.dto.community.CreatePostDto;
import com.cheeup.web.dto.community.ReadPostDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    //TODO: S3 파일 업로드 구현 추가하기

    // 게시글 상세 조회
    ReadPostDto.DetailResponseDto getPost(Long postId);

    // 게시글 목록 조회
    Page<ReadPostDto.ListResponseDto> getPostList(Long boardId, int page, int limit);

    // 게시글 작성
    void createPost(CreatePostDto.RequestDto requestDto);

    // 게시글 수정
    void updatePost();

    // 게시글 삭제
    void deletePost();

}
