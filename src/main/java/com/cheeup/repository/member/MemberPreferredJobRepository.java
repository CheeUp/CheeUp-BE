package com.cheeup.repository.member;

import com.cheeup.domain.member.Member;
import com.cheeup.domain.member.MemberPreferredJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPreferredJobRepository extends JpaRepository<MemberPreferredJob, Long> {
    List<MemberPreferredJob> findAllByMember(Member member);
}
