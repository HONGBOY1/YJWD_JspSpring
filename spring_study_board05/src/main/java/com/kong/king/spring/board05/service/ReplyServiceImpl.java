package com.kong.king.spring.board05.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kong.king.spring.board05.dto.ReplyDTO;
import com.kong.king.spring.board05.entity.Board;
import com.kong.king.spring.board05.entity.Reply;
import com.kong.king.spring.board05.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO dto) {
        replyRepository.save(entityToDto(dto));
        return dto.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        Board board = Board.builder().bno(bno).build();
        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(board);
        return result.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO dto) {
        replyRepository.save(entityToDto(dto));
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}