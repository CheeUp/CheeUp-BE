package com.cheeup.repository.jobnotice;

import com.cheeup.domain.jobnotice.JobNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JobNoticeRepository extends JpaRepository<JobNotice, Long> {
    @Query("SELECT j FROM JobNotice j " +
            "WHERE (j.startDate BETWEEN :startRange AND :endRange) " +
            "OR (j.endDate BETWEEN :startRange AND :endRange)")
    List<JobNotice> findAllByDateRange(@Param("startRange") LocalDate startRange,
                                       @Param("endRange") LocalDate endRange);
}
