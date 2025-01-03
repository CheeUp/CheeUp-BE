package com.cheeup.converter.jobnotice;


import com.cheeup.domain.jobnotice.JobNotice;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobNoticeMapper {

    JobNotice toEntity(PostJobNoticeDto.RequestDto dto);

}
