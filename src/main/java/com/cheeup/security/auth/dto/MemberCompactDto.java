package com.cheeup.security.auth.dto;

import com.cheeup.domain.enums.MemberRole;

import java.util.List;

public record MemberCompactDto(String memberId, List<MemberRole> roles) {
}
