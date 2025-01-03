package com.cheeup.web.dto.member;

import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

public class UpdateMemberDto {

    @Builder
    public record Request (
        @Size(min = 1, max = 20)
        String nickname,

        @Size(min = 1, max = 50)
        String email,

        @Size(max=20)
        String group,

        @Size(max=255)
        String profileImage,

        List<Long> skills,
        List<Long> preferredJobs
    ){}

}
