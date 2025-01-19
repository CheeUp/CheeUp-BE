package com.cheeup.security.auth.service;

import com.cheeup.domain.member.Member;
import com.cheeup.repository.member.MemberRepository;
import com.cheeup.security.auth.dto.MemberCompactDto;
import com.cheeup.security.auth.principal.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(username);

        if (findMember == null) {
            //TODO Exception
            throw new RuntimeException("없는 사용자");
        }

        MemberCompactDto dto = new MemberCompactDto(findMember.getId().toString(), List.of(findMember.getRole()));
        return new UserDetailsImpl(dto);
    }
}
