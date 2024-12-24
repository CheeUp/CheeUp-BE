package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.web.dto.JoinJobNoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JocNoticeRestController {

    @PostMapping("/job-notice")
    public ApiResponseDTO<JoinJobNoticeDTO.ResponseDTO> joinJobNotice(
            @RequestBody JoinJobNoticeDTO.RequestDTO requestDTO) {
        return null;
    }
}


