package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponse;
import com.cheeup.apiPayload.code.success.codes.CommunitySuccessCode;
import com.cheeup.service.community.BoardService;
import com.cheeup.web.dto.community.BoardDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시판 목록 조회
    @GetMapping("/boards")
    public ResponseEntity<ApiResponse<List<BoardDto.ResponseDto>>> getBoards() {
        List<BoardDto.ResponseDto> boardList = boardService.getBoardList();
        return ResponseEntity
                .status(CommunitySuccessCode.BOARD_LIST_FETCHED.getHttpStatus())
                .body(ApiResponse.onSuccess(CommunitySuccessCode.BOARD_LIST_FETCHED, boardList));
    }


    // 게시판 생성
    //TODO: 관리자 권한 확인
    @PostMapping("/board")
    public ResponseEntity<ApiResponse<Void>> createBoard(@Valid @RequestBody BoardDto.RequestDto requestDto) {
        boardService.createBoard(requestDto);
        return ResponseEntity
                .status(CommunitySuccessCode.BOARD_CREATED.getHttpStatus())
                .body(ApiResponse.onSuccess(CommunitySuccessCode.BOARD_CREATED, null));
    }

    // 게시판 수정
    //TODO: 관리자 권한 확인
    @PutMapping("/board/{boardId}")
    public ResponseEntity<ApiResponse<BoardDto.ResponseDto>> updateBoard(
            @Valid @RequestBody BoardDto.RequestDto requestDto,
            @PathVariable Long boardId) {
        boardService.updateBoard(boardId, requestDto);
        return ResponseEntity
                .status(CommunitySuccessCode.BOARD_UPDATED.getHttpStatus())
                .body(ApiResponse.onSuccess(CommunitySuccessCode.BOARD_UPDATED, null));
    }


    // 게시판 삭제
    //TODO: 관리자 권한 확인
    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity
                .status(CommunitySuccessCode.BOARD_DELETED.getHttpStatus())
                .body(ApiResponse.onSuccess(CommunitySuccessCode.BOARD_DELETED, null));
    }
}
