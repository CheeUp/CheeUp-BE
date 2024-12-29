package com.cheeup.repository.job;

import com.cheeup.domain.common.Job;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByName(String name);
}
