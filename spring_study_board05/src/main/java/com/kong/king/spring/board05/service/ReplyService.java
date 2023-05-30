package com.kong.king.spring.board05.service;

import java.util.List;

import com.kong.king.spring.board05.dto.ReplyDTO;
import com.kong.king.spring.board05.entity.Board;
import com.kong.king.spring.board05.entity.Reply;

public interface ReplyService {
    Long register(ReplyDTO dto);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO dto);

    void remove(Long rno);

    default Reply entityToDto(ReplyDTO dto) {
        Board board = Board.builder().bno(dto.getBno()).build();
        return Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
    }

    default ReplyDTO entityToDto(Reply entity) {
        return ReplyDTO.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replyer(entity.getReplyer())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}