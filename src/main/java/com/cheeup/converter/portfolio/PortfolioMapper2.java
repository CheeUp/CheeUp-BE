package com.cheeup.converter.portfolio;

import com.cheeup.domain.portfolio.Portfolio;
import com.cheeup.domain.portfolio.PortfolioAuthor;
import com.cheeup.domain.portfolio.PortfolioDisability;
import com.cheeup.domain.portfolio.PortfolioMilitary;
import com.cheeup.domain.portfolio.PortfolioSkill;
import com.cheeup.domain.portfolio.PortfolioVeteran;
import com.cheeup.web.dto.portfolio.ReadPortfolioDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioPersonalInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioSensitiveInfoDto;
import com.cheeup.web.dto.portfolio.ReadPortfolioTechSkillsDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortfolioMapper2 {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "job", source = "job.name")
    @Mapping(target = "open", source = "open")
    ReadPortfolioDto.ResponseDto toDto(Portfolio portfolio);

    @Mapping(target = "id", source = "portfolio.id")
    @Mapping(target = "personalInfo.id", source = "author.id")
    @Mapping(target = "personalInfo.name", source = "author.name")
    @Mapping(target = "personalInfo.email", source = "author.email")
    @Mapping(target = "personalInfo.introduction", source = "author.introduction")
    @Mapping(target = "personalInfo.githubUrl", source = "author.githubUrl")
    @Mapping(target = "personalInfo.blogUrl", source = "author.blogUrl")
    ReadPortfolioPersonalInfoDto.ResponseDto toDto(Portfolio portfolio, PortfolioAuthor author);

    @Mapping(target = "id", source = "portfolio.id")
    @Mapping(target = "techSkills", source = "portfolioSkills")
    ReadPortfolioTechSkillsDto.ResponseDto toDto(Portfolio portfolio, List<PortfolioSkill> portfolioSkills);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "skill.name")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "description", source = "description")
    ReadPortfolioTechSkillsDto.TechSkillDto toDto(PortfolioSkill portfolioSkill);

    @Mapping(target = "id", source = "portfolio.id")
    @Mapping(target = "sensitiveInfo.military", source = "military")
    @Mapping(target = "sensitiveInfo.veteran", source = "veteran")
    @Mapping(target = "sensitiveInfo.disability", source = "disability")
    ReadPortfolioSensitiveInfoDto.ResponseDto toSensitiveInfoDto(Portfolio portfolio, PortfolioMilitary military, PortfolioVeteran veteran, PortfolioDisability disability);
}
