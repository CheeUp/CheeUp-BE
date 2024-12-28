package com.cheeup.service.job;

import com.cheeup.web.dto.job.JoinJobNoticeDto;

public interface JobService {
    void join(JoinJobNoticeDto.RequestDto joinDTO);
}
