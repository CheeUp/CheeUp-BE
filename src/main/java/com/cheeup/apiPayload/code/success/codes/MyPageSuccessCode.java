package com.cheeup.apiPayload.code.success.codes;

import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MyPageSuccessCode implements SuccessCode {
    MY_PAGE_READ(HttpStatus.OK, "MYPAGE2001", "내 게시물 조회 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
