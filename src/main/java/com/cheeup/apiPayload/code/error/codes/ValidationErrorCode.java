package com.cheeup.apiPayload.code.error.codes;


import com.cheeup.apiPayload.code.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ValidationErrorCode implements ErrorCode {

    NOT_FOUND_FILE_TYPE(HttpStatus.NOT_FOUND, "VALID4001", "파일 타입을 찾을 수 없습니다."),
    NOT_FOUND_JOB_DESCRIPTION_TYPE(HttpStatus.NOT_FOUND, "VALID4002", "취업 공고 타입을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
