package com.cheeup.security.auth.dto;

import com.cheeup.domain.enums.MemberRole;

import java.util.List;

public record OauthMemberDto(String email, String name, List<MemberRole> roles, String socialType, String socialId) {
}
