package com.cheeup.service.jobnotice;

import com.cheeup.web.dto.jobnotice.ReadScrappedJobNoticeDto;

public interface MyJobNoticeService {

    ReadScrappedJobNoticeDto.ResponseDto readScrappedJobNotice(Long memberId, int page, int limit);

}
