package com.cheeup.service.member;

import com.cheeup.apiPayload.code.error.codes.JobErrorCode;
import com.cheeup.apiPayload.code.error.codes.MemberErrorCode;
import com.cheeup.apiPayload.code.error.codes.SkillErrorCode;
import com.cheeup.apiPayload.exception.handler.JobException;
import com.cheeup.apiPayload.exception.handler.MemberException;
import com.cheeup.apiPayload.exception.handler.SkillException;
import com.cheeup.converter.member.MemberMapper;
import com.cheeup.domain.common.Job;
import com.cheeup.domain.common.Skill;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final MemberPreferredJobRepository memberPreferredJobRepository;
    private final MemberMapper memberMapper;
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;

    @Override
    public ReadMemberDto.ResponseDto getMemberInfo(long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        List<MemberSkill> memberSkill = memberSkillRepository.findAllByMember(findMember);
        List<MemberPreferredJob> preferredJobs = memberPreferredJobRepository.findAllByMember(findMember);

        List<String> memberSkillList = extractMemberSkillList(memberSkill);
        List<String> preferredJobList = extractMemberPreferredJobList(preferredJobs);

        ReadMemberDto.ResponseDto response = memberMapper.toDto(findMember, memberSkillList, preferredJobList);

        return response;
    }

    @Override
    @Transactional
    public void updateMemberInfo(long id, UpdateMemberDto.Request request) {
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        Member pendingMemberInfo = memberMapper.toUpdatedEntity(request, findMember);
        memberRepository.save(pendingMemberInfo);

        clearMemberSkills(findMember);
        List<MemberSkill> memberSkillList = mapMemberSkillsFromRequest(request, findMember);
        memberSkillRepository.saveAll(memberSkillList);

        clearMemberPreferredJobs(findMember);
        List<MemberPreferredJob> memberPreferredJobList = mapMemberPreferredJobsFromRequest(request, findMember);
        memberPreferredJobRepository.saveAll(memberPreferredJobList);
    }

    private List<MemberSkill> mapMemberSkillsFromRequest(UpdateMemberDto.Request request, Member member) {
        return request.skills().stream()
                .map( skillId -> createMemberSkill(skillId, member))
                .toList();
    }

    private List<MemberPreferredJob> mapMemberPreferredJobsFromRequest(UpdateMemberDto.Request request, Member member) {
        return request.preferredJobs().stream()
                .map( jobId -> createMemberPreferredJob(jobId, member))
                .toList();
    }

    private MemberSkill createMemberSkill(Long skillId, Member member) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(
                () -> new SkillException(SkillErrorCode.SKILL_NOT_FOUND)
        );

        return MemberSkill.builder()
                .skill(skill)
                .member(member)
                .build();
    }

    private MemberPreferredJob createMemberPreferredJob(Long jobId, Member member) {
        Job job = jobRepository.findById(jobId).orElseThrow(
                () -> new JobException(JobErrorCode.JOB_NOT_FOUND)
        );

        return MemberPreferredJob.builder()
                .job(job)
                .member(member)
                .build();
    }

    private void clearMemberSkills(Member member) {
        List<Long> skillIdList = memberSkillRepository.findAllByMember(member)
                .stream()
                .map(MemberSkill::getId)
                .toList();

        memberSkillRepository.deleteAllByIdInBatch(
                skillIdList
        );
    }

    private void clearMemberPreferredJobs(Member member) {
        List<Long> jobIdList = memberPreferredJobRepository.findAllByMember(member)
                .stream()
                .map(MemberPreferredJob::getId)
                .toList();

        memberPreferredJobRepository.deleteAllByIdInBatch(
                jobIdList
        );
    }

    private List<String> extractMemberSkillList(List<MemberSkill> memberSkillList) {
        return memberSkillList.stream().map(
                item -> item.getSkill().getName()
        ).toList();
    }

    private List<String> extractMemberPreferredJobList(List<MemberPreferredJob> memberJobList) {
        return memberJobList.stream().map(
                item -> item.getJob().getName()
        ).toList();
    }
}
