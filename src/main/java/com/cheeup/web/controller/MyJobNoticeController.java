package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.MyScrappedJobNoticeSuccessCode;
import com.cheeup.service.jobnotice.MyJobNoticeService;
import com.cheeup.web.dto.jobnotice.ReadScrappedJobNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyJobNoticeController {

    private final MyJobNoticeService myJobNoticeService;

    @GetMapping("/mypage/job-notice/{memberId}")
    public ResponseEntity<ApiResponse<ReadScrappedJobNoticeDto.ResponseDto>> readScrappedJobNotice(
            @PathVariable Long memberId,
            @RequestParam(
                    required = false,
                    defaultValue = "0"
            ) int page,
            @RequestParam(
                    required = false,
                    defaultValue = "10"
            ) int limit
    ) {
        ReadScrappedJobNoticeDto.ResponseDto response = myJobNoticeService.readScrappedJobNotice(memberId, page, limit);
        return ResponseEntity
                .status(MyScrappedJobNoticeSuccessCode.MY_SCRAPPED_JOB_NOTICE_READ_SUCCESS.getHttpStatus())
                .body(ApiResponse.onSuccess(MyScrappedJobNoticeSuccessCode.MY_SCRAPPED_JOB_NOTICE_READ_SUCCESS, response));

    }

}
