package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.JobNoticeSuccessCode;
import com.cheeup.service.jobnotice.JobNoticeService;
import com.cheeup.web.dto.jobnotice.GetAllJobNoticesDto;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobNoticeController {
    private final JobNoticeService jobNoticeService;

    @PostMapping("/job-notice")
    public ResponseEntity<ApiResponse<Void>> createJobNotice(
            @Valid @RequestBody PostJobNoticeDto.RequestDto requestDto) {
        jobNoticeService.createJobNotice(requestDto);
        return ResponseEntity
                .status(JobNoticeSuccessCode.JOB_NOTICE_APPLY.getHttpStatus())
                .body(ApiResponse.onSuccess(JobNoticeSuccessCode.JOB_NOTICE_APPLY, null));
    }

    @GetMapping("/job-notice")
    public ResponseEntity<ApiResponse<List<GetAllJobNoticesDto.ResponseDto>>> readJobNoticeByYearAndMonth(
            @RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        List<GetAllJobNoticesDto.ResponseDto> result = jobNoticeService.readJobNoticeByYearAndMonth(year, month);
        return ResponseEntity
                .status(JobNoticeSuccessCode.JOB_NOTICE_READ_ALL.getHttpStatus())
                .body(ApiResponse.onSuccess(JobNoticeSuccessCode.JOB_NOTICE_READ_ALL, result));
    }
}


