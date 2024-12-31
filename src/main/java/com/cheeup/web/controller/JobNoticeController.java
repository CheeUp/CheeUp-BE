package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.JobNoticeSuccessCode;
import com.cheeup.service.jobnotice.JobNoticeService;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobNoticeController {
    private final JobNoticeService jobNoticeService;

    @PostMapping("/job-notice")
    public ResponseEntity<ApiResponse<Void>> createJobNotice(
            @Valid @RequestBody PostJobNoticeDto.RequestDto requestDto) {
        jobNoticeService.createJobNotice(requestDto);
        return ResponseEntity
                .status(JobNoticeSuccessCode.JOB_APPLY.getHttpStatus())
                .body(ApiResponse.onSuccess(JobNoticeSuccessCode.JOB_APPLY, null));
    }
}


