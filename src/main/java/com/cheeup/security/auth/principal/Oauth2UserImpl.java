package com.cheeup.security.auth.principal;

import com.cheeup.security.auth.dto.OauthMemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;


public class Oauth2UserImpl implements OAuth2User {

    private final OauthMemberDto dto;

    public Oauth2UserImpl(OauthMemberDto dto) {
        this.dto = dto;
    }

    // Provider 마다 데이터 타입이 너무 달라 구현 x
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = dto.roles()
                .stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new).toList();
        return authorities;
    }

    @Override
    public String getName() {
        return dto.name();
    }

    public String getEmail() {
        return dto.email();
    }

    public String getSocialId() {
        return dto.socialId();
    }

}
