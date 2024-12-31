package com.cheeup.service.member;

import com.cheeup.converter.member.MemberConverter;
import com.cheeup.domain.common.Job;
import com.cheeup.domain.common.Skill;
import com.cheeup.domain.member.Member;
import com.cheeup.domain.member.MemberPreferredJob;
import com.cheeup.domain.member.MemberSkill;
import com.cheeup.repository.member.JobRepository;
import com.cheeup.repository.member.MemberPreferredJobRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.repository.member.MemberSkillRepository;
import com.cheeup.repository.member.SkillRepository;
import com.cheeup.web.dto.ReadMemberDto;
import com.cheeup.web.dto.UpdateMemberDto;
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
    private final MemberConverter memberConverter;
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;

    @Override
    public ReadMemberDto.Response getMemberInfo(long id) {

        Member findMember = memberRepository.findById(id).orElseThrow();

        List<MemberSkill> memberSkill = memberSkillRepository.findAllByMember(findMember);
        List<MemberPreferredJob> preferredJobs = memberPreferredJobRepository.findAllByMember(findMember);

        List<String> memberSkillList = extractMemberSkillList(memberSkill);
        List<String> preferredJobList = extractMemberPreferredJobList(preferredJobs);

        return memberConverter.toResponse(findMember, memberSkillList, preferredJobList);
    }

    @Override
    @Transactional
    public void updateMemberInfo(long id, UpdateMemberDto.Request request) {
        Member findMember = memberRepository.findById(id).orElseThrow();

        Member pendingMemberInfo = memberConverter.toUpdatedEntity(request, findMember);
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
        Skill skill = skillRepository.findById(skillId).orElseThrow();
        return MemberSkill.builder()
                .skill(skill)
                .member(member)
                .build();
    }

    private MemberPreferredJob createMemberPreferredJob(Long jobId, Member member) {
        Job job = jobRepository.findById(jobId).orElseThrow();
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