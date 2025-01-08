package com.cheeup.repository.comunity;

import com.cheeup.domain.community.Post;
import com.cheeup.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyPageRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByMember(Member member, Pageable pageable);
}
