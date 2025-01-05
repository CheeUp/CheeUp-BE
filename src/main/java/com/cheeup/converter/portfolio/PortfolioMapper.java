package com.cheeup.converter.portfolio;

import com.cheeup.domain.portfolio.Portfolio;
import com.cheeup.web.dto.portfolio.CreatePortfolioDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {
    Portfolio toEntity(CreatePortfolioDto.RequestDto requestDto);
}
