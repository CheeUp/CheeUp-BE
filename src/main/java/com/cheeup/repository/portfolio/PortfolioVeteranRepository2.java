package com.cheeup.repository.portfolio;

import com.cheeup.domain.portfolio.PortfolioVeteran;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioVeteranRepository2 extends JpaRepository<PortfolioVeteran, Long> {
    Optional<PortfolioVeteran> findByPortfolioId(Long portfolioId);
}