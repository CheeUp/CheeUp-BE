package com.cheeup.apiPayload.code.error.codes;


import com.cheeup.apiPayload.code.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum CommunityErrorCode implements ErrorCode {

    BOARD_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "COMMUNITY4001", "이미 존재하는 게시판입니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMUNITY4002", "존재하지 않는 게시판입니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMUNITY4003", "존재하지 않는 게시글입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
