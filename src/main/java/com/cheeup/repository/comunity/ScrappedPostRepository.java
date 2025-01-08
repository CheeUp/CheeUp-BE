package com.cheeup.repository.comunity;

import com.cheeup.domain.community.PostScrap;
import com.cheeup.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrappedPostRepository extends JpaRepository<PostScrap, Long> {

    Page<PostScrap> findAllByMember(Member member, Pageable pageable);
}
