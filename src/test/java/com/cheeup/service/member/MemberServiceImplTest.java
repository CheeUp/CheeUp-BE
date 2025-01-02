package com.cheeup.service.member;

import com.cheeup.converter.member.MemberMapper;
import com.cheeup.converter.member.MemberConverterImpl;
import com.cheeup.domain.common.Job;
import com.cheeup.domain.common.Skill;
import com.cheeup.domain.enums.MemberRole;
import com.cheeup.domain.member.Member;
import com.cheeup.domain.member.MemberPreferredJob;
import com.cheeup.domain.member.MemberSkill;
import com.cheeup.repository.common.JobRepository;
import com.cheeup.repository.common.SkillRepository;
import com.cheeup.repository.member.MemberPreferredJobRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.repository.member.MemberSkillRepository;
import com.cheeup.web.dto.member.ReadMemberDto;
import com.cheeup.web.dto.member.UpdateMemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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

    @Mock
    JobRepository jobRepository;

    @Mock
    SkillRepository skillRepository;
    
    @InjectMocks
    MemberServiceImpl memberServiceImpl;

    @Spy
    private MemberMapper memberMapper = new MemberConverterImpl();

    private Member member;
    private MemberSkill memberSkill;
    private MemberPreferredJob memberPreferredJob;
    private ReadMemberDto.ResponseDto response;
    private Skill skill;
    private Job job;

    @BeforeEach
    void init() {
        member = createMemberAndSave();

        skill = Skill.builder()
                .name("자바")
                .build();

        job = Job.builder()
                .name("백엔드")
                .build();

        memberSkill = MemberSkill.builder()
                .member(member)
                .skill(skill)
                .build();

        memberPreferredJob = MemberPreferredJob.builder()
                .job(job)
                .build();

        response = memberMapper.toDto(
                member,
                List.of("자바"),
                List.of("백엔드")
        );

    }

    @Test
    @DisplayName("올바른 값 -> 성공")
    void getMemberInfo() {
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(memberSkillRepository.findAllByMember(member)).willReturn(List.of(memberSkill));
        given(memberPreferredJobRepository.findAllByMember(member)).willReturn(List.of(memberPreferredJob));

        ReadMemberDto.ResponseDto memberInfo = memberServiceImpl.getMemberInfo(1L);
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

    @Test
    @DisplayName("유저 수정 - 성공")
    void updateMemberInfo () {
        UpdateMemberDto.Request request = createUpdateMemberRequest();

        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(memberSkillRepository.findAllByMember(member)).willReturn(List.of(memberSkill));
        given(memberPreferredJobRepository.findAllByMember(member)).willReturn(List.of(memberPreferredJob));
        givenSkillAndJob();

        memberServiceImpl.updateMemberInfo(1L, request);
        then(memberSkillRepository).should().findAllByMember(member);
    }

    void givenSkillAndJob() {
        given(skillRepository.findById(12L)).willReturn(Optional.of(skill));
        given(skillRepository.findById(13L)).willReturn(Optional.of(skill));
        given(jobRepository.findById(2L)).willReturn(Optional.of(job));
        given(jobRepository.findById(3L)).willReturn(Optional.of(job));
    }


    UpdateMemberDto.Request createUpdateMemberRequest() {
        return UpdateMemberDto.Request.builder()
                .nickname("김싸피")
                .email("kim-ssafy@example.com")
                .group("SSAFY 12기")
                .profileImage("https://kakao.com/profile-image.jpg")
                .skills(List.of(12L, 13L))
                .preferredJobs(List.of(2L, 3L))
                .build();
    }
}