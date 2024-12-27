package com.cheeup.service.job;

import com.cheeup.web.dto.JoinJobNoticeDTO;

public interface JobService {
    void join(JoinJobNoticeDTO.RequestDTO joinDTO);
}
