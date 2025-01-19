package com.cheeup.security.auth.service;


import com.cheeup.domain.enums.MemberRole;
import com.cheeup.domain.member.Member;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.security.auth.dto.KakaoOauthResponse;
import com.cheeup.security.auth.dto.Oauth2Response;
import com.cheeup.security.auth.dto.OauthMemberDto;
import com.cheeup.security.auth.principal.Oauth2UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        Oauth2Response oauth2Response = resolveResponseByProviderId(registrationId, oAuth2User);

        Member findMember = memberRepository.findByEmail(oauth2Response.getEmail());

        if (findMember == null) {
            OauthMemberDto memberDto = new OauthMemberDto(oauth2Response.getEmail(), oauth2Response.getName(), List.of(MemberRole.GUEST), registrationId, oauth2Response.getProviderId());
            return new Oauth2UserImpl(memberDto);
        } else {
            OauthMemberDto memberDto = new OauthMemberDto(oauth2Response.getEmail(), oauth2Response.getName(), List.of(MemberRole.MEMBER), registrationId, oauth2Response.getProviderId());
            return new Oauth2UserImpl(memberDto);
        }
    }

    private Oauth2Response resolveResponseByProviderId(String providerId, OAuth2User oAuth2User) {
        if (providerId == null) {
            //TODO Exception
            throw new RuntimeException("");
        }
        return switch (providerId) {
            case "kakao" -> new KakaoOauthResponse(oAuth2User.getAttributes());
            default -> null;
        };
    }
}
