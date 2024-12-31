package com.cheeup.converter.jobnotice;

import com.cheeup.domain.jobnotice.JobDescription;
import com.cheeup.web.dto.jobnotice.JobNoticeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobDescriptionMapper {

    JobDescriptionMapper INSTANCE = Mappers.getMapper(JobDescriptionMapper.class);

    JobDescription toEntity(JobNoticeDto.JobDescriptionDto dto);

}
