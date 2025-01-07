package com.cheeup.apiPayload.code.success.codes;

import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PortfolioSuccessCode implements SuccessCode {
    PORTFOLIO_CREATE_SUCCESS(HttpStatus.CREATED, "PORTFOLIO2001", "포트폴리오 생성에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
