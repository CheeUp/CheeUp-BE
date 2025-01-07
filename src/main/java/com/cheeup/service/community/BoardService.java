package com.cheeup.service.community;

import com.cheeup.web.dto.community.BoardDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {
    // 게시판 생성 (관리자)
    void createBoard(BoardDto.RequestDto request);

    // 전체 게시판 목록 조회
    List<BoardDto.ResponseDto> getBoardList();

    // 게시판 정보 수정 (관리자)
    void updateBoard(Long boardId, BoardDto.RequestDto request);

    // 게시판 삭제 (관리자)
    void deleteBoard(Long boardId);
}
