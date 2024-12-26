package com.cheeup.web.dto;

import lombok.Builder;

import java.util.List;

public class MemberDTO {

    @Builder
    public record ResponseDTO(
        String nickname,
        String email,
        String group,
        String profileImage,
        List<String> skills,
        List<String> preferredJobs,
        boolean isActivated
    ) {}
}
