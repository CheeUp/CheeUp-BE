package com.cheeup.service.portfolio;

import com.cheeup.apiPayload.code.error.codes.JobErrorCode;
import com.cheeup.apiPayload.exception.handler.ResourceNotFoundException;
import com.cheeup.converter.portfolio.PortfolioMapper;
import com.cheeup.domain.common.Job;
import com.cheeup.domain.member.Member;
import com.cheeup.domain.portfolio.Portfolio;
import com.cheeup.repository.common.JobRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.repository.portfolio.PortfolioRepository;
import com.cheeup.web.dto.portfolio.CreatePortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final JobRepository jobRepository;
    private final PortfolioMapper portfolioMapper;
    private final MemberRepository memberRepository;

    @Override
    public void createPortfolioBasicInfo(CreatePortfolioDto.RequestDto requestDto) {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("d"));
        Job job = jobRepository.findById(requestDto.jobId()).orElseThrow(() -> new ResourceNotFoundException(
                JobErrorCode.JOB_NOT_FOUND));

        Portfolio portfolio = portfolioMapper.toEntity(requestDto);
        portfolio.updateMember(member);
        portfolio.updateJob(job);

        portfolioRepository.save(portfolio);
    }
}
