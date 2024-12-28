package com.cheeup.service.job;

import com.cheeup.web.dto.job.PostJobNoticeDto;

public interface JobService {
    void createJoinNotice(PostJobNoticeDto.RequestDto joinDTO);
}
