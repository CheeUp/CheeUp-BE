package com.cheeup.service.community;

import com.cheeup.apiPayload.code.error.codes.CommunityErrorCode;
import com.cheeup.apiPayload.exception.handler.BadRequestException;
import com.cheeup.apiPayload.exception.handler.NotFoundException;
import com.cheeup.converter.community.BoardMapper;
import com.cheeup.domain.community.Board;
import com.cheeup.repository.BoardRepository;
import com.cheeup.web.dto.community.BoardDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public void createBoard(BoardDto.RequestDto requestDto) {
        // 존재하는 게시판인지 확인
        if (boardRepository.existsByName(requestDto.name())) {
            throw new BadRequestException(CommunityErrorCode.BOARD_ALREADY_EXISTS);
        }
        // 게시판 추가
        boardRepository.save(boardMapper.toEntity(requestDto));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BoardDto.ResponseDto> getBoardList() {
        return boardRepository.findAll().stream()
                .map(boardMapper::toBoardDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateBoard(Long boardId, BoardDto.RequestDto request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(CommunityErrorCode.BOARD_NOT_FOUND));
        // 게시판 수정
        board.updateBoard(request);
    }

    @Transactional
    @Override
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(CommunityErrorCode.BOARD_NOT_FOUND));
        // 게시판 삭제
        boardRepository.delete(board);
    }
}
