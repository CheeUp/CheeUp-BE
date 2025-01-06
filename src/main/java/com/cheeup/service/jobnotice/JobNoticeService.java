package com.cheeup.service.jobnotice;

import com.cheeup.web.dto.jobnotice.GetAllJobNoticesDto;
import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;

import java.util.List;

public interface JobNoticeService {
    void createJobNotice(PostJobNoticeDto.RequestDto requestDto);

    List<GetAllJobNoticesDto.ResponseDto> readJobNoticeByYearAndMonth(int year, int month);
}
