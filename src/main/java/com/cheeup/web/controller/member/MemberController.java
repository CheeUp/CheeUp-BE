package com.cheeup.web.controller.member;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.converter.MemberConverter;
import com.cheeup.domain.member.MemberInfo;
import com.cheeup.service.member.MemberUseCase;
import com.cheeup.web.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final MemberConverter memberConverter;

    // 인증 인가 구현 되기 전까지 path variable 로 개발
    @GetMapping("/{id}")
    public ApiResponseDTO<MemberDTO.ResponseDTO> getMemberInfo(@PathVariable Long id){
        MemberInfo memberInfo = memberUseCase.getMemberInfo(id);

        return ApiResponseDTO.onSuccess(
                memberConverter.toResponseDTO(memberInfo)
        );
    }
}
