package com.cheeup.web.controller.member;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.MemberSuccessCode;
import com.cheeup.service.member.MemberService;
import com.cheeup.web.dto.member.CreateMemberDto;
import com.cheeup.web.dto.member.ReadMemberDto;
import com.cheeup.web.dto.member.UpdateMemberDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 인증 인가 구현 되기 전까지 path variable 로 개발
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReadMemberDto.ResponseDto>> getMemberInfo(@PathVariable long id){
        ReadMemberDto.ResponseDto memberInfo = memberService.getMemberInfo(id);

        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_READ.getHttpStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_READ, memberInfo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateMemberInfo(
            @PathVariable long id,
            @Valid @RequestBody UpdateMemberDto.Request request
    ) {
        memberService.updateMemberInfo(id, request);

        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_UPDATE.getHttpStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_UPDATE, null));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createMember(
            @Valid @RequestBody CreateMemberDto.Request request
    ) {
        memberService.createMember(request);

        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_CREATE.getHttpStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_CREATE, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> createMember(
            @PathVariable long id
    ) {
        memberService.deleteMember(id);

        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_DELETE.getHttpStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_DELETE, null));
    }
}
