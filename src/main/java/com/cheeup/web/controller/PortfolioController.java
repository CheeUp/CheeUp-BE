package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.PortfolioSuccessCode;
import com.cheeup.service.portfolio.PortfolioService;
import com.cheeup.web.dto.portfolio.CreatePortfolioDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/portfolio")
    public ResponseEntity<ApiResponse<Void>> createPortfolioBasicInfo(
            @RequestBody @Valid CreatePortfolioDto.RequestDto requestDto) {

        portfolioService.createPortfolioBasicInfo(requestDto);

        return ResponseEntity.status(PortfolioSuccessCode.PORTFOLIO_CREATE_SUCCESS.getHttpStatus())
                .body(ApiResponse.onSuccess(PortfolioSuccessCode.PORTFOLIO_CREATE_SUCCESS, null));
    }

}
