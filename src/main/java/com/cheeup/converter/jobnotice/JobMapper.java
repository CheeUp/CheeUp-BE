package com.cheeup.converter.jobnotice;

import com.cheeup.domain.common.Job;
import com.cheeup.web.dto.jobnotice.JobNoticeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    Job toEntity(JobNoticeDto.JobDto dto);


}