package com.cheeup.security.auth.principal;

import com.cheeup.domain.enums.MemberRole;
import com.cheeup.security.auth.dto.MemberCompactDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails {

    private final MemberCompactDto dto;

    public UserDetailsImpl(MemberCompactDto dto) {
        this.dto = dto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<? extends GrantedAuthority> authorities = dto.roles()
                .stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
        return authorities;
    }

    // 사용자 고유 식별자.
    @Override
    public String getUsername() {
        return getMemberId();
    }

    @Override
    public String getPassword() {
        return "";
    }

    public String getMemberId() {
        return dto.memberId();
    }

    public List<MemberRole> getMemberRole() {
        return dto.roles();
    }

}
