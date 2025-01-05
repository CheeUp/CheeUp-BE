package com.cheeup.converter.member;

import com.cheeup.domain.member.Member;
import com.cheeup.web.dto.member.ReadMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target="profileImage", source ="member.profileImageUrl")
    ReadMemberDto.ResponseDto toDto(Member member, List<String> skills, List<String> preferredJobs);
}