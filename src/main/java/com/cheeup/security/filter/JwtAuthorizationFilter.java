package com.cheeup.security.filter;

import com.cheeup.constant.CookieConstant;
import com.cheeup.domain.enums.MemberRole;
import com.cheeup.security.auth.dto.MemberCompactDto;
import com.cheeup.security.auth.principal.UserDetailsImpl;
import com.cheeup.util.CookieUtil;
import com.cheeup.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 토큰 검증
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Optional<String> optionalToken = cookieUtil.extractCookieValue(request, CookieConstant.AUTH_TOKEN_KEY);
        if (optionalToken.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }
        String token = optionalToken.get();

        String memberId = jwtUtil.getMemberId(token);
        List<String> roles = jwtUtil.getRoles(token);
        List<MemberRole> memberRoles = roles.stream().map(MemberRole::valueOf).toList();

        MemberCompactDto memberDto = new MemberCompactDto(memberId, memberRoles);

        Collection<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetailsImpl userDetails = new UserDetailsImpl(memberDto);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}

