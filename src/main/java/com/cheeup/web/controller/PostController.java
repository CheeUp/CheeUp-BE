package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.CommunitySuccessCode;
import com.cheeup.service.community.PostService;
import com.cheeup.web.dto.community.CreatePostDto;
import com.cheeup.web.dto.community.ReadPostDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post/{boardId}")
    public ResponseEntity<ApiResponse<Void>> createPost(
            @Valid @RequestBody CreatePostDto.RequestDto requestDto,
            @PathVariable Long boardId) {
        postService.createPost(requestDto);
        return ResponseEntity
                .status(CommunitySuccessCode.BOARD_CREATED.getHttpStatus())
                .body(ApiResponse.onSuccess(CommunitySuccessCode.POST_CREATED, null));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<ReadPostDto.DetailResponseDto>> getPost(@PathVariable Long postId) {
        return ResponseEntity
                .status(CommunitySuccessCode.POST_FETCHED.getHttpStatus())
                .body(ApiResponse.onSuccess(CommunitySuccessCode.POST_FETCHED, postService.getPost(postId)));
    }

    //    @GetMapping("/post/{boardId}/{page}/{limit}")
    @GetMapping("/posts/{boardId}")
    public ResponseEntity<ApiResponse<Page<ReadPostDto.ListResponseDto>>> getPostList(
            @PathVariable Long boardId
//            , @PathVariable int page,            @PathVariable int limit
    ) {
        return ResponseEntity
                .status(CommunitySuccessCode.POST_LIST_FETCHED.getHttpStatus())
                .body(ApiResponse.onSuccess(CommunitySuccessCode.POST_LIST_FETCHED, postService.getPostList(boardId, 1, 10)));
    }
}
