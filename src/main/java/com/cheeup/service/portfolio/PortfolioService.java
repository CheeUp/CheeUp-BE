package com.cheeup.service.portfolio;

import com.cheeup.web.dto.portfolio.CreatePortfolioDto;

public interface PortfolioService {
    public void createPortfolioBasicInfo(CreatePortfolioDto.RequestDto requestDto);
}
