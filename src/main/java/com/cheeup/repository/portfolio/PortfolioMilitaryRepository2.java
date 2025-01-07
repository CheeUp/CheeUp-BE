package com.cheeup.repository.portfolio;

import com.cheeup.domain.portfolio.PortfolioMilitary;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioMilitaryRepository2 extends JpaRepository<PortfolioMilitary, Long> {
    Optional<PortfolioMilitary> findByPortfolioId(Long portfolioId);
}
