package com.cheeup.web.controller.post;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.MyPageSuccessCode;
import com.cheeup.service.community.MyPageService;
import com.cheeup.web.dto.community.ReadMyPostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponse<ReadMyPostsDto.ResponseDto>> getMyPosts(
            @PathVariable long id,
            @RequestParam(
                    required = false,
                    defaultValue = "1"
            ) int page,
            @RequestParam(
                    required = false,
                    defaultValue = "10"
            ) int limit
    ){
        ReadMyPostsDto.ResponseDto myPosts = myPageService.getMyPosts(id, page, limit);

        return ResponseEntity
                .status(MyPageSuccessCode.MY_PAGE_READ.getHttpStatus())
                .body(ApiResponse.onSuccess(MyPageSuccessCode.MY_PAGE_READ, myPosts));
    }

}
