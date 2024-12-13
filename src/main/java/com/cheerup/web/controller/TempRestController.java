package com.cheerup.web.controller;

import com.cheerup.apiPayload.ApiResponseDTO;
import com.cheerup.converter.TempConverter;
import com.cheerup.web.dto.temp.TempResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    @GetMapping("/test")
    public ApiResponseDTO<TempResponseDTO.TempTestDTO> testAPI(){
        return ApiResponseDTO.onSuccess(TempConverter.toTempTestDTO());
    }
}


