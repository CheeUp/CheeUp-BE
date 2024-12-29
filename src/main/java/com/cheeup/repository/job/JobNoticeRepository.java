package com.cheeup.repository.job;

import com.cheeup.domain.job.JobNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobNoticeRepository extends JpaRepository<JobNotice, Long> {
}
