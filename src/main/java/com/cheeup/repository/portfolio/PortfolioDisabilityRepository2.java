package com.cheeup.repository.portfolio;

import com.cheeup.domain.portfolio.PortfolioDisability;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioDisabilityRepository2 extends JpaRepository<PortfolioDisability, Long> {
    Optional<PortfolioDisability> findByPortfolioId(Long portfolioId);
}
