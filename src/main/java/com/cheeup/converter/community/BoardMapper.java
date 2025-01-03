package com.cheeup.converter.community;

import com.cheeup.domain.community.Board;
import com.cheeup.web.dto.community.BoardDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board toEntity(BoardDto.RequestDto requestDto);

    BoardDto.ResponseDto toBoardDto(Board board);
}
