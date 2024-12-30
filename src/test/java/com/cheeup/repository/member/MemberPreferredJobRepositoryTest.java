package com.cheeup.repository.member;

import com.cheeup.domain.enums.MemberRole;
import com.cheeup.domain.member.Member;
import com.cheeup.domain.member.MemberPreferredJob;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberPreferredJobRepositoryTest {

    @Autowired
    private MemberPreferredJobRepository memberPreferredJobRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = createMemberAndSave();
        createMemberPreferredJobAndSave();
    }

    @Test
    @DisplayName("회원 선호 직업 조회")
    void findAllByMember() {
        Member findMember = memberRepository.findById((long) member.getId()).orElseThrow();

        List<MemberPreferredJob> preferredJobList = memberPreferredJobRepository.findAllByMember(findMember);

        assertFalse(preferredJobList.isEmpty());
    }

    private Member createMemberAndSave() {
        Member createMember = Member.builder()
                .nickname("nickname")
                .email("test@test.com")
                .kakaoId("kakao")
                .name("jungChan")
                .experience(100)
                .role(MemberRole.MEMBER)
                .githubUrl("githubUrl")
                .build();

        memberRepository.save(createMember);

        return createMember;
    }

    private void createMemberPreferredJobAndSave() {
        List<String> jobNmList = List.of("백엔드", "프론트", "인프라");
        List<MemberPreferredJob> preferList = new ArrayList<>();

        jobNmList.forEach(
                nm -> preferList.add(
                        MemberPreferredJob.builder()
                                .jobName(nm)
                                .member(member)
                                .build()
                )
        );

        memberPreferredJobRepository.saveAll(preferList);
    }
}