package com.cheeup.apiPayload.code.error.codes;

import com.cheeup.apiPayload.code.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum JobNoticeErrorCode implements ErrorCode {
    JOB_DESCRIPTION_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "JOB4001", "존재하지 않는 채용 형태입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
