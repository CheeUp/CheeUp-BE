package com.cheeup.converter.member;

import com.cheeup.domain.member.Member;
import com.cheeup.web.dto.ReadMemberDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberConverter {

    ReadMemberDto.Response toResponse(Member member, List<String> skills, List<String> preferredJobs);
}