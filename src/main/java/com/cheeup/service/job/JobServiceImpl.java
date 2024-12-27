package com.cheeup.service.job;

import com.cheeup.web.dto.JoinJobNoticeDTO.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobServiceImpl implements JobService {
    @Override
    public void join(RequestDTO joinDTO) {
        //TODO 관리자가 등록하는 건지 확인하는 로직
        
    }
}
