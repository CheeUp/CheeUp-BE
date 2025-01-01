package com.cheeup.repository.member;

import com.cheeup.domain.member.Member;
import com.cheeup.domain.member.MemberSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberSkillRepository extends JpaRepository<MemberSkill, Long> {
    List<MemberSkill> findAllByMember(Member member);
}
