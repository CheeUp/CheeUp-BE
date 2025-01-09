package com.cheeup.service.jobnotice;

import com.cheeup.apiPayload.code.error.codes.MemberErrorCode;
import com.cheeup.apiPayload.exception.handler.MemberException;
import com.cheeup.converter.jobnotice.MyJobNoticeMapper;
import com.cheeup.domain.jobnotice.JobNotice;
import com.cheeup.domain.jobnotice.JobNoticeScrap;
import com.cheeup.domain.member.Member;
import com.cheeup.repository.jobnotice.JobNoticeScrapRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.web.dto.common.Pagination;
import com.cheeup.web.dto.jobnotice.ReadScrappedJobNoticeDto;
import com.cheeup.web.dto.jobnotice.ScrappedJobNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyJobNoticeServiceImpl implements MyJobNoticeService {

    private final JobNoticeScrapRepository jobNoticeScrapRepository;
    private final MemberRepository memberRepository;
    private final MyJobNoticeMapper myJobNoticeMapper;

    @Override
    public ReadScrappedJobNoticeDto.ResponseDto readScrappedJobNotice(Long memberId, int page, int limit) {

        Page<JobNoticeScrap> jobNoticeScrapPage = getJobNoticeScrapPage(memberId, page, limit);

        List<JobNotice> JobNoticeList = getJobNotice(jobNoticeScrapPage);

        List<ScrappedJobNoticeDto.JobNotice> scrappedJobNoticeList = JobNoticeList.stream().map(
                myJobNoticeMapper::toScrappedJobNoticeDto
        ).toList();

        Pagination pagination = Pagination.builder()
                .currentPage(page)
                .totalPages(jobNoticeScrapPage.getTotalPages())
                .totalCount(jobNoticeScrapPage.getTotalElements())
                .pageSize(limit)
                .build();

        return myJobNoticeMapper.toDto(pagination, scrappedJobNoticeList);
    }

    private Page<JobNoticeScrap> getJobNoticeScrapPage(Long memberId, int page, int limit) {

        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        return jobNoticeScrapRepository.findALlByMember(
                findMember,
                PageRequest.of(page, limit)
        );
    }

    private List<JobNotice> getJobNotice(Page<JobNoticeScrap> jobNoticeScrapPage) {
        return jobNoticeScrapPage.getContent().stream().map(
                JobNoticeScrap::getJobNotice
        ).toList();
    }
}
