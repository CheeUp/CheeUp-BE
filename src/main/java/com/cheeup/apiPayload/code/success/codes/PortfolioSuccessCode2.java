package com.cheeup.apiPayload.code.success.codes;

import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PortfolioSuccessCode2 implements SuccessCode {
    PORTFOLIO_READ(HttpStatus.OK, "PORTFOLIO2001", "포트폴리오 조회에 성공하였습니다."),
    MY_PORTFOLIO_READ(HttpStatus.OK, "PORTFOLIO2002", "내 포트폴리오 개인정보 조회에 성공하였습니다."),
    PORTFOLIO_SKILLS_READ(HttpStatus.OK, "PORTFOLIO2003", "포트폴리오 기술 스택 조회에 성공하였습니다."),
    PORTFOLIO_SENSITIVE_INFO_READ(HttpStatus.OK, "PORTFOLIO2004", "포트폴리오 민감 정보 조회에 성공하였습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
