package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.service.job.JobService;
import com.cheeup.web.dto.job.JoinJobNoticeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobNoticeRestController {
    private final JobService jobService;

    @PostMapping("/job-notice")
    public ApiResponseDTO joinJobNotice(@Valid @RequestBody JoinJobNoticeDto.RequestDto requestDto) {
        jobService.join(requestDto);
        return ApiResponseDTO.onSuccess(null);
    }
}


