package com.cheeup.web.controller.portfolio;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.PortfolioSuccessCode2;
import com.cheeup.service.portfolio.PortfolioService2;
import com.cheeup.web.dto.portfolio.ReadPortfolioDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioPersonalInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioSensitiveInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioTechSkillsDto;
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
                .status(PortfolioSuccessCode2.PORTFOLIO_READ.getHttpStatus())
                .body(ApiResponse.onSuccess(PortfolioSuccessCode2.PORTFOLIO_READ, responseDto));
    }

    @GetMapping("/portfolio/{portfolioId}/personal-info")
    public ResponseEntity<ApiResponse<ReadPortfolioPersonalInfoDto.ResponseDto>> getPortfolioPersonalInfo(@PathVariable Long portfolioId) {
        var responseDto = portfolioService.getPersonalInfo(portfolioId);

        return ResponseEntity
                .status(PortfolioSuccessCode2.MY_PORTFOLIO_READ.getHttpStatus())
                .body(ApiResponse.onSuccess(PortfolioSuccessCode2.MY_PORTFOLIO_READ, responseDto));
    }

    @GetMapping("/portfolio/{portfolioId}/tech-skills")
    public ResponseEntity<ApiResponse<ReadPortfolioTechSkillsDto.ResponseDto>> getTechSkills(@PathVariable Long portfolioId) {
        var responseDto = portfolioService.getTechSkills(portfolioId);

        return ResponseEntity
                .status(PortfolioSuccessCode2.PORTFOLIO_SKILLS_READ.getHttpStatus())
                .body(ApiResponse.onSuccess(PortfolioSuccessCode2.PORTFOLIO_SKILLS_READ, responseDto));
    }

    @GetMapping("/{portfolioId}/sensitive-info")
    public ResponseEntity<ApiResponse<ReadPortfolioSensitiveInfoDto.ResponseDto>> getSensitiveInfo(@PathVariable Long portfolioId) {
        var responseDto = portfolioService.getSensitiveInfo(portfolioId);

        return ResponseEntity
                .status(PortfolioSuccessCode2.PORTFOLIO_SENSITIVE_INFO_READ.getHttpStatus())
                .body(ApiResponse.onSuccess(PortfolioSuccessCode2.PORTFOLIO_SENSITIVE_INFO_READ, responseDto));
    }
}
