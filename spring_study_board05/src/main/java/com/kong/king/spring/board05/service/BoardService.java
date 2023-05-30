package com.kong.king.spring.board05.service;

import com.kong.king.spring.board05.dto.BoardDTO;
import com.kong.king.spring.board05.dto.PageRequestDTO;
import com.kong.king.spring.board05.dto.PageResultDTO;
import com.kong.king.spring.board05.entity.Board;
import com.kong.king.spring.board05.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO);

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDTO boardDTO);

    default Board entityToDto(BoardDTO dto) {
        Member writer = Member.builder().email(dto.getWriterEmail()).build();
        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(writer)
                .build();
    }

    default BoardDTO entityToDto(Board board, Member member, Long replyCount) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCount(replyCount)
                .build();
    }
}