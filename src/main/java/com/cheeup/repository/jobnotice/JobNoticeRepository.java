package com.cheeup.repository.jobnotice;

import com.cheeup.domain.jobnotice.JobNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobNoticeRepository extends JpaRepository<JobNotice, Long> {
}
