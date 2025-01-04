package com.cheeup.web.dto.member;

import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

public class CreateMemberDto {

    @Builder
    public record Request (

            String kakaoId,

            @Size(min = 1, max = 20)
            String nickname,

            @Size(min = 1, max = 50)
            String email,

            @Size(min = 1, max = 20)
            String name,

            @Size(max=20)
            String groups,

            @Size(max=255)
            String profileImageUrl,

            @Size(max=255)
            String githubUrl,

            List<Long> skills,
            List<Long> preferredJobs
    ){}

}
