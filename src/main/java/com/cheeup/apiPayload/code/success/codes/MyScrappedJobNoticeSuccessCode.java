package com.cheeup.apiPayload.code.success.codes;

import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MyScrappedJobNoticeSuccessCode implements SuccessCode {
    MY_SCRAPPED_JOB_NOTICE_READ_SUCCESS(HttpStatus.OK, "SCRAPNOTICE2001", "내 스크랩 공고 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
