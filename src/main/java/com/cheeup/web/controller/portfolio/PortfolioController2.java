package com.cheeup.web.controller.portfolio;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.MemberSuccessCode;
import com.cheeup.apiPayload.code.success.codes.PortfolioSuccessCode2;
import com.cheeup.service.portfolio.PortfolioService2;
import com.cheeup.web.dto.portfolio.ReadPortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PortfolioController2 {
    private final PortfolioService2 portfolioService;

    @GetMapping("/portfolio/{id}")
    public ResponseEntity<ApiResponse<ReadPortfolioDto.ResponseDto>> searchPortfolio(@PathVariable Long id) {
        var responseDto = portfolioService.searchPortfolio(id);

        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_READ.getHttpStatus())
                .body(ApiResponse.onSuccess(PortfolioSuccessCode2.PORTFOLIO_READ, responseDto));
    }
}
