package com.cheeup.security.config;

import com.cheeup.domain.enums.MemberRole;
import com.cheeup.domain.member.Member;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.security.auth.principal.Oauth2UserImpl;
import com.cheeup.util.CookieUtil;
import com.cheeup.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Component
public class OauthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final CookieUtil cookieUtil;
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final String CLIENT_URL;


    public OauthSuccessHandler(CookieUtil cookieUtil, JwtUtil jwtUtil, MemberRepository memberRepository, @Value("${client.url}") String clientUrl) {
        this.cookieUtil = cookieUtil;
        this.jwtUtil = jwtUtil;
        this.memberRepository = memberRepository;
        this.CLIENT_URL = clientUrl;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Oauth2UserImpl oauth2User = (Oauth2UserImpl) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<MemberRole> memberRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(MemberRole::valueOf)
                .toList();

        // TODO MemberRole이 여럿일경우 수정
        MemberRole memberRole = memberRoles.get(0);

        String email = oauth2User.getEmail();
        switch (memberRole) {
            case MEMBER:
                System.out.println("email = " + email);
                Member findMember = memberRepository.findByEmail(email);
                String token = jwtUtil.createAccessToken(String.valueOf(findMember.getId()), List.of(findMember.getRole()));
                response.addCookie(cookieUtil.createCookie("Authorization", token));
                System.out.println(request.getRequestURI());
                System.out.println(request.getRequestURL().toString());
                System.out.println(request.getRemoteHost());
                System.out.println(request.getLocalAddr());
                System.out.println(request.getRemoteAddr());
                System.out.println(request.getRemotePort());
                response.sendRedirect(CLIENT_URL);
                break;
            case GUEST:
                response.addCookie(cookieUtil.createCookie("social_id", oauth2User.getSocialId()));
                response.addCookie(cookieUtil.createCookie("social_email", oauth2User.getEmail()));
                response.addCookie(cookieUtil.createCookie("social_name", Base64.getEncoder().encodeToString(oauth2User.getName().getBytes(StandardCharsets.UTF_8))));
                response.sendRedirect(CLIENT_URL); // TODO회원가입 주소
                break;
            case ADMIN:
                break;
            default:
                break;
        }
    }

}
