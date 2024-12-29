package com.cheeup.service;

import com.cheeup.apiPayload.code.status.ErrorStatus;
import com.cheeup.apiPayload.exception.handler.CommunityHandler;
import com.cheeup.converter.community.BoardConverter;
import com.cheeup.domain.community.Board;
import com.cheeup.repository.BoardRepository;
import com.cheeup.web.dto.community.board.BoardDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardDto.ResponseDto createBoard(BoardDto.RequestDto requestDto) {
        // 존재하는 게시판인지 확인
        if (boardRepository.existsByName(requestDto.name())) {
            throw new CommunityHandler(ErrorStatus._BOARD_ALREADY_EXISTS);
        }
        // 게시판 추가
        Board board = boardRepository.save(BoardConverter.toEntity(requestDto));
        return BoardConverter.toDto(board);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BoardDto.ResponseDto> getBoardList() {
        return boardRepository.findAll().stream()
                .map(BoardConverter::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BoardDto.ResponseDto updateBoard(Long boardId, BoardDto.RequestDto request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CommunityHandler(ErrorStatus._BOARD_NOT_FOUND));
        // 게시판 수정
        board.updateBoard(request);
        return BoardConverter.toDto(board);
    }

    @Transactional
    @Override
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CommunityHandler(ErrorStatus._BOARD_NOT_FOUND));
        // 게시판 삭제
        boardRepository.delete(board);
        
    }
}
