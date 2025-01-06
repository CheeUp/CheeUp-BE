package com.cheeup.service.portfolio;

import com.cheeup.web.dto.portfolio.ReadPortfolioDto;

public interface PortfolioService2 {
    ReadPortfolioDto.ResponseDto searchPortfolio(Long id);
}
