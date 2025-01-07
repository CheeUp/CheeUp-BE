package com.cheeup.service.portfolio;

import com.cheeup.apiPayload.code.error.codes.PortfolioErrorCode2;
import com.cheeup.apiPayload.exception.handler.NotFoundException;
import com.cheeup.converter.portfolio.PortfolioMapper2;
import com.cheeup.domain.portfolio.Portfolio;
import com.cheeup.domain.portfolio.PortfolioAuthor;
import com.cheeup.domain.portfolio.PortfolioDisability;
import com.cheeup.domain.portfolio.PortfolioMilitary;
import com.cheeup.domain.portfolio.PortfolioSkill;
import com.cheeup.domain.portfolio.PortfolioVeteran;
import com.cheeup.repository.portfolio.PortfolioAuthorRepository2;
import com.cheeup.repository.portfolio.PortfolioAwardRepository2;
import com.cheeup.repository.portfolio.PortfolioCareerRepository2;
import com.cheeup.repository.portfolio.PortfolioCertificateRepository2;
import com.cheeup.repository.portfolio.PortfolioDisabilityRepository2;
import com.cheeup.repository.portfolio.PortfolioFeedbackLikeRepository2;
import com.cheeup.repository.portfolio.PortfolioFeedbackRepository2;
import com.cheeup.repository.portfolio.PortfolioGraduateMajorRepository2;
import com.cheeup.repository.portfolio.PortfolioGraduateRepository2;
import com.cheeup.repository.portfolio.PortfolioHighSchoolRepository2;
import com.cheeup.repository.portfolio.PortfolioLanguageRepository2;
import com.cheeup.repository.portfolio.PortfolioMilitaryRepository2;
import com.cheeup.repository.portfolio.PortfolioProjectFileRepository2;
import com.cheeup.repository.portfolio.PortfolioProjectRepository2;
import com.cheeup.repository.portfolio.PortfolioProjectSkillRepository2;
import com.cheeup.repository.portfolio.PortfolioRepository2;
import com.cheeup.repository.portfolio.PortfolioSkillRepository2;
import com.cheeup.repository.portfolio.PortfolioThesisRepository2;
import com.cheeup.repository.portfolio.PortfolioTrainingRepository2;
import com.cheeup.repository.portfolio.PortfolioUniversityMajorRepository2;
import com.cheeup.repository.portfolio.PortfolioUniversityRepository2;
import com.cheeup.repository.portfolio.PortfolioVeteranRepository2;
import com.cheeup.web.dto.portfolio.ReadPortfolioDto.ResponseDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioPersonalInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioSensitiveInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioTechSkillsDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl2 implements PortfolioService2 {
    private final PortfolioRepository2 portfolioRepository;
    private final PortfolioAuthorRepository2 portfolioAuthorRepository;
    private final PortfolioAwardRepository2 portfolioAwardRepository;
    private final PortfolioCareerRepository2 portfolioCareerRepository;
    private final PortfolioCertificateRepository2 portfolioCertificateRepository;
    private final PortfolioDisabilityRepository2 portfolioDisabilityRepository;
    private final PortfolioFeedbackRepository2 portfolioFeedbackRepository;
    private final PortfolioFeedbackLikeRepository2 portfolioFeedbackLikeRepository;
    private final PortfolioGraduateRepository2 portfolioGraduateRepository;
    private final PortfolioGraduateMajorRepository2 portfolioGraduateMajorRepository;
    private final PortfolioHighSchoolRepository2 portfolioHighSchoolRepository;
    private final PortfolioUniversityRepository2 portfolioUniversityRepository;
    private final PortfolioUniversityMajorRepository2 portfolioUniversityMajorRepository;
    private final PortfolioLanguageRepository2 portfolioLanguageRepository;
    private final PortfolioMilitaryRepository2 portfolioMilitaryRepository;
    private final PortfolioProjectRepository2 portfolioProjectRepository;
    private final PortfolioProjectSkillRepository2 portfolioProjectSkillRepository;
    private final PortfolioProjectFileRepository2 portfolioProjectFileRepository;
    private final PortfolioSkillRepository2 portfolioSkillRepository;
    private final PortfolioThesisRepository2 portfolioThesisRepository;
    private final PortfolioTrainingRepository2 portfolioTrainingRepository;
    private final PortfolioVeteranRepository2 portfolioVeteranRepository;
    private final PortfolioMapper2 portfolioMapper2;

    // 포트폴리오 조회
    public ResponseDto searchPortfolio(Long id) {
        return portfolioMapper2.toDto(findPortfolio(id));
    }

    // 포트폴리오 개인정보 조회
    @Transactional(readOnly = true)
    public ReadPortfolioPersonalInfoDto.ResponseDto getPersonalInfo(Long portfolioId) {
        Portfolio portfolio = findPortfolio(portfolioId);

        PortfolioAuthor author = portfolioAuthorRepository.findByPortfolioId(portfolioId)
                .orElseThrow(() -> new NotFoundException(PortfolioErrorCode2.PORTFOLIO_AUTHOR_NOT_FOUND));

        return portfolioMapper2.toDto(portfolio, author);
    }

    // 포트폴리오 기술 스택 조회
    @Transactional(readOnly = true)
    public ReadPortfolioTechSkillsDto.ResponseDto getTechSkills(Long portfolioId) {
        Portfolio portfolio = findPortfolio(portfolioId);

        List<PortfolioSkill> portfolioSkills = portfolioSkillRepository.findByPortfolioId(portfolioId);

        return portfolioMapper2.toDto(portfolio, portfolioSkills);
    }

    // 포트폴리오 민감 정보 조회
    @Transactional(readOnly = true)
    public ReadPortfolioSensitiveInfoDto.ResponseDto getSensitiveInfo(Long portfolioId) {
        Portfolio portfolio = findPortfolio(portfolioId);

        PortfolioMilitary military = portfolioMilitaryRepository.findByPortfolioId(portfolioId)
                .orElseThrow(() -> new NotFoundException(PortfolioErrorCode2.MILITARY_NOT_FOUND));

        PortfolioVeteran veteran = portfolioVeteranRepository.findByPortfolioId(portfolioId)
                .orElseThrow(() -> new NotFoundException(PortfolioErrorCode2.VETERAN_NOT_FOUND));

        PortfolioDisability disability = portfolioDisabilityRepository.findByPortfolioId(portfolioId)
                .orElseThrow(() -> new NotFoundException(PortfolioErrorCode2.DISABILITY_NOT_FOUND));

        return portfolioMapper2.toSensitiveInfoDto(portfolio, military, veteran, disability);
    }

    @Transactional(readOnly = true)
    public Portfolio findPortfolio(Long id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PortfolioErrorCode2.PORTFOLIO_NOT_FOUND));
    }
}
