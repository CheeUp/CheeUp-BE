package com.cheeup.converter.member;

import com.cheeup.domain.member.Member;
import com.cheeup.web.dto.ReadMemberDto;
import com.cheeup.web.dto.UpdateMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberConverter {

    ReadMemberDto.Response toResponse(Member member, List<String> skills, List<String> preferredJobs);

    Member toUpdatedEntity(UpdateMemberDto.Request request, @MappingTarget Member member);
}