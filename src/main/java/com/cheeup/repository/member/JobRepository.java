package com.cheeup.repository.member;


import com.cheeup.domain.common.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
