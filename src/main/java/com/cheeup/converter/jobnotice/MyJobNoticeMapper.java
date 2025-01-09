package com.cheeup.converter.jobnotice;

import com.cheeup.domain.jobnotice.JobNotice;
import com.cheeup.web.dto.common.Pagination;
import com.cheeup.web.dto.jobnotice.ReadScrappedJobNoticeDto;
import com.cheeup.web.dto.jobnotice.ScrappedJobNoticeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyJobNoticeMapper {

    ReadScrappedJobNoticeDto.ResponseDto toDto(Pagination pagination, List<ScrappedJobNoticeDto.JobNotice> jobNotices);

    @Mapping(target = "companyName" , source = "company")
    @Mapping(target = "jobNoticeLink" , source = "url")
    ScrappedJobNoticeDto.JobNotice toScrappedJobNoticeDto(JobNotice jobNotice);
}
