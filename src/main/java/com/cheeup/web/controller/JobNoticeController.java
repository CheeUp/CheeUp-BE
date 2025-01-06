package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.JobNoticeSuccessCode;
import com.cheeup.service.jobnotice.JobNoticeService;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobNoticeController {
    private final JobNoticeService jobNoticeService;

    @Transactional
    @PostMapping("/job-notice")
    public ResponseEntity<ApiResponse<Void>> createJobNotice(
            @Valid @RequestBody PostJobNoticeDto.RequestDto requestDto) {
        jobNoticeService.createJobNotice(requestDto);
        return ResponseEntity
                .status(JobNoticeSuccessCode.JOB_APPLY.getHttpStatus())
                .body(ApiResponse.onSuccess(JobNoticeSuccessCode.JOB_APPLY, null));
    }

    @GetMapping("/job-notice")
    public ResponseEntity<ApiResponse<Void>> readJobNoticeByYearAndMonth(
            @RequestParam("year") Integer year, @RequestParam("month") Integer month) {

        return ResponseEntity
                .status(JobNoticeSuccessCode.JOB_APPLY.getHttpStatus())
                .body(ApiResponse.onSuccess(JobNoticeSuccessCode.JOB_APPLY, null));
    }
}


