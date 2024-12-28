package com.cheeup.repository.common;

import com.cheeup.domain.common.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
