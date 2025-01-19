package com.cheeup.security.filter;

import com.cheeup.constant.CookieConstant;
import com.cheeup.security.auth.dto.MemberLogin;
import com.cheeup.security.auth.principal.UserDetailsImpl;
import com.cheeup.util.CookieUtil;
import com.cheeup.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 일반 로그인
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;

    private final ObjectMapper om;

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("### JwtAuthenticationFilter :: attempt");

        try {
            MemberLogin loginRequestDto = om.readValue(request.getInputStream(), MemberLogin.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.userId(), loginRequestDto.password());
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO Exception Handling
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authenticate) throws IOException, ServletException {
        log.info("### JwtAuthenticationFilter :: success");
        final UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        String memberId = userDetails.getMemberId();

        final String token = jwtUtil.createAccessToken(memberId, userDetails.getMemberRole());
        Cookie cookie = cookieUtil.createCookie(CookieConstant.AUTH_TOKEN_KEY, token);
        response.addCookie(cookie);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        super.successfulAuthentication(request, response, chain, authenticate);
    }


    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
