package com.cheeup.converter.community;

import com.cheeup.domain.community.Board;
import com.cheeup.web.dto.community.board.BoardDto;
import com.cheeup.web.dto.community.board.BoardDto.ResponseDto;

public class BoardConverter {

    //TODO: exception 처리
    public static Board toEntity(BoardDto.RequestDto requestDto) {
//        if (requestDto == null) {
//            throw new IllegalArgumentException("requestDto is null");
//        }

        return Board.builder()
                .name(requestDto.name())
                .isAnonymous(requestDto.isAnonymous())
                .category(requestDto.category())
                .build();
    }

    public static BoardDto.ResponseDto toDto(Board board) {
//        if (board == null) {
//            throw new IllegalArgumentException("board is null");
//        }

        return ResponseDto.builder()
                .id(board.getId())
                .name(board.getName())
                .isAnonymous(board.getIsAnonymous())
                .category(board.getCategory())
                .build();
    }
}
