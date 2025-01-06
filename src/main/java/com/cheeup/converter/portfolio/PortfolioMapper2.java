package com.cheeup.converter.portfolio;

import com.cheeup.domain.portfolio.Portfolio;
import com.cheeup.web.dto.portfolio.ReadPortfolioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortfolioMapper2 {
    @Mapping(target = "memberName", source = "member.name")
    @Mapping(target = "jobName", source = "job.name")
    @Mapping(target = "skills", source = "portfolioSkillList")
    ReadPortfolioDto.ResponseDto toDto(Portfolio portfolio);

    
}
