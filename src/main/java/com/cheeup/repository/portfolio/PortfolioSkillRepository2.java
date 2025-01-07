package com.cheeup.repository.portfolio;

import com.cheeup.domain.portfolio.PortfolioSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioSkillRepository2 extends JpaRepository<PortfolioSkill, Long> {
    List<PortfolioSkill> findByPortfolioId(Long portfolioId);
}
