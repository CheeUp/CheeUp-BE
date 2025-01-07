package com.cheeup.repository.portfolio;

import com.cheeup.domain.portfolio.PortfolioAuthor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioAuthorRepository2 extends JpaRepository<PortfolioAuthor, Long> {
    Optional<PortfolioAuthor> findByPortfolioId(Long portfolioId);
}
