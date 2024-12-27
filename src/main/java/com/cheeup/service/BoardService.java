package com.cheeup.service;

import com.cheeup.web.dto.community.board.BoardDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {
    // 게시판 생성 (관리자)
    BoardDto.Response createBoard(BoardDto.Request request);

    // 전체 게시판 목록 조회
    List<BoardDto.Response> getBoardList();

    // 게시판 정보 수정 (관리자)
    BoardDto.Response updateBoard(Long boardId, BoardDto.Request request);

    // 게시판 삭제 (관리자)
    void deleteBoard(Long boardId);
}
