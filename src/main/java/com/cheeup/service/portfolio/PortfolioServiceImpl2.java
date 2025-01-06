package com.cheeup.service.portfolio;

import com.cheeup.converter.portfolio.PortfolioMapper2;
import com.cheeup.domain.portfolio.Portfolio;
import com.cheeup.repository.portfolio.PortfolioRepository2;
import com.cheeup.web.dto.portfolio.ReadPortfolioDto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl2 implements PortfolioService2 {
    private final PortfolioRepository2 portfolioRepository;
    private final PortfolioMapper2 portfolioMapper2;

    @Transactional(readOnly = true)
    public ResponseDto searchPortfolio(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow();

        return portfolioMapper2.toDto(portfolio);
    }
}
