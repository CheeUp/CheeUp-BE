package com.cheeup.service.jobnotice;

import com.cheeup.web.dto.jobnotice.PostJobNoticeDto;

public interface JobNoticeService {
    void createJobNotice(PostJobNoticeDto.RequestDto requestDto);
}
