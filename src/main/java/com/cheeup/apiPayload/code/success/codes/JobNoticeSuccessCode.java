package com.cheeup.apiPayload.code.success.codes;

import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum JobNoticeSuccessCode implements SuccessCode {
    JOB_APPLY(HttpStatus.OK, "JOB2001", "취업 공고 등록에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
