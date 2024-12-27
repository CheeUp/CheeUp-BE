package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.apiPayload.code.status.SuccessStatus;
import com.cheeup.service.job.JobService;
import com.cheeup.web.dto.JoinJobNoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobNoticeRestController {
    private final JobService jobService;

    @PostMapping("/job-notice")
    public ApiResponseDTO joinJobNotice(
            @RequestBody JoinJobNoticeDTO.RequestDTO requestDTO) {
        jobService.join(requestDTO);
        return ApiResponseDTO.of(SuccessStatus._JOB_APPLIY, null);
    }
}


