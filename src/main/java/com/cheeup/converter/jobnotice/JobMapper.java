package com.cheeup.converter.jobnotice;

import com.cheeup.domain.common.Job;
import com.cheeup.web.dto.jobnotice.JobNoticeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toEntity(JobNoticeDto.JobDto dto);


}