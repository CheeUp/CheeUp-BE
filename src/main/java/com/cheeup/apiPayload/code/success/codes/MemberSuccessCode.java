package com.cheeup.apiPayload.code.success.codes;

import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements SuccessCode {
    MEMBER_READ(HttpStatus.OK, "MEMBER2001", "회원 조회에 성공했습니다."),
    MEMBER_UPDATE(HttpStatus.OK, "MEMBER2002", "회원 수정에 성공했습니다."),
    MEMBER_CREATE(HttpStatus.CREATED, "MEMBER2003", "회원 생성에 성공했습니다."),
    MEMBER_DELETE(HttpStatus.OK , "MEMBER2004", "회원 삭제에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
