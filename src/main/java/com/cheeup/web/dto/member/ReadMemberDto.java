package com.cheeup.web.dto.member;

import lombok.Builder;

import java.util.List;

public class ReadMemberDto {

    @Builder
    public record ResponseDto(
            String nickname,
            String email,
            String groups,
            String profileImage,
            List<String> skills,
            List<String> preferredJobs,
            boolean isActivated
    ) {}
}
