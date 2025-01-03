package com.cheeup.apiPayload.code.success.codes;

import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommunitySuccessCode implements SuccessCode {
    BOARD_CREATED(HttpStatus.CREATED, "COMMUNITY2001", "게시판 등록에 성공하였습니다."),
    BOARD_LIST_FETCHED(HttpStatus.OK, "COMMUNITY2002", "게시판 목록 조회를 성공했습니다."),
    BOARD_UPDATED(HttpStatus.OK, "COMMUNITY2003", "게시판 수정에 성공하였습니다."),
    BOARD_DELETED(HttpStatus.OK, "COMMUNITY2004", "게시판 삭제에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
