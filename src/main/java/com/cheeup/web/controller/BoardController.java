package com.cheeup.web.controller;

import com.cheeup.apiPayload.ApiResponseDTO;
import com.cheeup.service.BoardService;
import com.cheeup.web.dto.community.board.BoardDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public ApiResponseDTO<List<BoardDto.ResponseDto>> getBoards() {
        return ApiResponseDTO.onSuccess(boardService.getBoardList());
    }

    // 게시판 생성
    //TODO: 관리자 권한 확인
    @PostMapping("/board")
    public ApiResponseDTO<BoardDto.ResponseDto> createBoard(@RequestBody BoardDto.RequestDto requestDto) {
        return ApiResponseDTO.onSuccess(boardService.createBoard(requestDto));
    }

    // 게시판 수정
    //TODO: 관리자 권한 확인
    @PutMapping("/board/{boardId}")
    public ApiResponseDTO<BoardDto.ResponseDto> updateBoard(@RequestBody BoardDto.RequestDto requestDto,
                                                            @PathVariable Long boardId) {
        return ApiResponseDTO.onSuccess(boardService.updateBoard(boardId, requestDto));
    }

    // 게시판 삭제
    //TODO: 관리자 권한 확인
    @DeleteMapping("/board/{boardId}")
    public ApiResponseDTO<String> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ApiResponseDTO.onSuccess("게시판 삭제 성공");
    }
}
