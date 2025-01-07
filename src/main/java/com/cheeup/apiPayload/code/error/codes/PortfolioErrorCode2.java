package com.cheeup.apiPayload.code.error.codes;

import com.cheeup.apiPayload.code.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PortfolioErrorCode2 implements ErrorCode {
    PORTFOLIO_NOT_FOUND(HttpStatus.NOT_FOUND, "PORTFOLIO4001", "존재하지 않는 포트폴리오입니다."),
    PORTFOLIO_AUTHOR_NOT_FOUND(HttpStatus.NOT_FOUND, "PORTFOLIO4002", "존재하지 않는 포트폴리오 유저 입니다."),
    MILITARY_NOT_FOUND(HttpStatus.NOT_FOUND, "PORTFOLIO4003", "존재하지 않는 포트폴리오 유저 입니다."),
    VETERAN_NOT_FOUND(HttpStatus.NOT_FOUND, "PORTFOLIO4004", "존재하지 않는 포트폴리오 유저 입니다."),
    DISABILITY_NOT_FOUND(HttpStatus.NOT_FOUND, "PORTFOLIO4005", "존재하지 않는 포트폴리오 유저 입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
