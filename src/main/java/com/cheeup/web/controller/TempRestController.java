package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.converter.TempConverter;
import com.cheeup.web.dto.TempDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    @GetMapping("/test")
    public ApiResponseDTO<TempDTO.ResponseDTO> testAPI(){
        return ApiResponseDTO.onSuccess(TempConverter.toTempDTO());
    }
}


