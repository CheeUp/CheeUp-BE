package com.cheeup.converter.jobnotice;

import com.cheeup.domain.jobnotice.JobDescription;
import com.cheeup.web.dto.jobnotice.JobNoticeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobDescriptionMapper {

    JobDescription toEntity(JobNoticeDto.JobDescriptionDto dto);

}
