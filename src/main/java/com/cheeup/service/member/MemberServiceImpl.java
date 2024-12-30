package com.cheeup.service.member;

import com.cheeup.converter.member.MemberConverter;
import com.cheeup.domain.member.Member;
import com.cheeup.domain.member.MemberPreferredJob;
import com.cheeup.domain.member.MemberSkill;
import com.cheeup.repository.member.MemberPreferredJobRepository;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.repository.member.MemberSkillRepository;
import com.cheeup.web.dto.ReadMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final MemberPreferredJobRepository memberPreferredJobRepository;
    private final MemberConverter memberConverter;

    @Override
    public ReadMemberDto.Response getMemberInfo(long id) {

        Member findMember = memberRepository.findById(id).orElseThrow();

        List<MemberSkill> memberSkill = memberSkillRepository.findAllByMember(findMember);
        List<MemberPreferredJob> preferredJobs = memberPreferredJobRepository.findAllByMember(findMember);

        List<String> memberSkillList = extractMemberSkillList(memberSkill);
        List<String> preferredJobList = extractMemberPreferredJobList(preferredJobs);

        return memberConverter.toResponse(findMember, memberSkillList, preferredJobList);
    }

    private List<String> extractMemberSkillList(List<MemberSkill> memberSkill) {
        return memberSkill.stream().map(
                item -> item.getSkill().getName()
        ).toList();
    }

    private List<String> extractMemberPreferredJobList(List<MemberPreferredJob> memberSkill) {
        return memberSkill.stream().map(
                MemberPreferredJob::getJobName
        ).toList();
    }
}
