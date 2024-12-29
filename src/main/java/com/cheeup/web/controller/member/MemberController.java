package com.cheeup.web.controller.member;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.service.member.MemberService;
import com.cheeup.web.dto.ReadMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 인증 인가 구현 되기 전까지 path variable 로 개발
    @GetMapping("/{id}")
    public ApiResponseDTO<ReadMemberDto.Response> getMemberInfo(@PathVariable long id){

        ReadMemberDto.Response memberInfo = memberService.getMemberInfo(id);

        return ApiResponseDTO.onSuccess(
                memberInfo
        );
    }
}