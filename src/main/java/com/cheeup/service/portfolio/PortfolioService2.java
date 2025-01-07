package com.cheeup.service.portfolio;

import com.cheeup.web.dto.portfolio.ReadPortfolioDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioPersonalInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioSensitiveInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioTechSkillsDto;

public interface PortfolioService2 {
    ReadPortfolioDto.ResponseDto searchPortfolio(Long id);

    ReadPortfolioPersonalInfoDto.ResponseDto getPersonalInfo(Long portfolioId);

    ReadPortfolioTechSkillsDto.ResponseDto getTechSkills(Long portfolioId);

    ReadPortfolioSensitiveInfoDto.ResponseDto getSensitiveInfo(Long portfolioId);
}