package com.cheeup.service.member;

import com.cheeup.converter.member.MemberConverter;
import com.cheeup.converter.member.MemberConverterImpl;
import com.cheeup.domain.common.Skill;
import com.cheeup.domain.enums.MemberRole;
import com.cheeup.domain.member.Member;
import com.cheeup.domain.member.MemberPreferredJob;
import com.cheeup.domain.member.MemberSkill;
import com.cheeup.repository.member.MemberPreferredJobRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.repository.member.MemberSkillRepository;
import com.cheeup.web.dto.ReadMemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberSkillRepository memberSkillRepository;

    @Mock
    MemberPreferredJobRepository memberPreferredJobRepository;

    @InjectMocks
    MemberServiceImpl memberServiceImpl;

    @Spy
    private MemberConverter memberConverter = new MemberConverterImpl();

    private Member member;
    private MemberSkill memberSkill;
    private MemberPreferredJob memberPreferredJob;
    private ReadMemberDto.Response response;

    @BeforeEach
    void init() {
        member = createMemberAndSave();
        memberSkill = MemberSkill.builder()
                .member(member)
                .skill(
                        Skill.builder()
                            .id(1L)
                            .name("자바")
                            .build()
                ).build();

        memberPreferredJob = MemberPreferredJob.builder()
                .jobName("백엔드")
                .createdAt(LocalDateTime.now())
                .build();

        response = memberConverter.toResponse(
                member,
                List.of("자바"),
                List.of("백엔드")
        );

    }

    @Test
    @DisplayName("올바른 값 -> 성공")
    void getMemberInfo() {
        given(memberRepository.findById(1L)).willReturn(member);
        given(memberSkillRepository.findAllByMember(member)).willReturn(List.of(memberSkill));
        given(memberPreferredJobRepository.findAllByMember(member)).willReturn(List.of(memberPreferredJob));

        ReadMemberDto.Response memberInfo = memberServiceImpl.getMemberInfo(1L);
        Assertions.assertThat(memberInfo).isEqualTo(response);

        then(memberSkillRepository).should().findAllByMember(member);

        System.out.println("getMemberInfo 결과 = " + memberInfo);
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

}