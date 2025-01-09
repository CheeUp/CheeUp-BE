package com.cheeup.repository.jobnotice;

import com.cheeup.domain.jobnotice.JobNoticeScrap;
import com.cheeup.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobNoticeScrapRepository extends JpaRepository<JobNoticeScrap, Long> {
    Page<JobNoticeScrap> findALlByMember(Member member, Pageable pageable);
}
