package com.cheeup.converter.jobnotice;


import com.cheeup.domain.jobnotice.JobNotice;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobNoticeMapper {
    JobNoticeMapper INSTANCE = Mappers.getMapper(JobNoticeMapper.class);

    JobNotice toEntity(PostJobNoticeDto.RequestDto dto);

}
