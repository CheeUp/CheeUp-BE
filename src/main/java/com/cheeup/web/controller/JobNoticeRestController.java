package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.converter.job.JobNoticeConverter;
import com.cheeup.service.job.JobService;
import com.cheeup.web.dto.job.PostJobNoticeDto;
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
    public ApiResponseDTO<PostJobNoticeDto.ResponseDto> joinJobNotice(
            @Valid @RequestBody PostJobNoticeDto.RequestDto requestDto) {
        jobService.createJoinNotice(requestDto);
        return ApiResponseDTO.onSuccess(JobNoticeConverter.toResponseDto());
    }
}


