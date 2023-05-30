package com.kong.king.spring.board05.service;


import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kong.king.spring.board05.dto.BoardDTO;
import com.kong.king.spring.board05.dto.PageRequestDTO;
import com.kong.king.spring.board05.dto.PageResultDTO;
import com.kong.king.spring.board05.entity.Board;
import com.kong.king.spring.board05.entity.Member;
import com.kong.king.spring.board05.repository.BoardRepository;
import com.kong.king.spring.board05.repository.ReplyRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);
        Board board = entityToDto(dto);
        boardRepository.save(board);
        
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequseDTO) {
        log.info(pageRequseDTO);
        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board) en[0], (Member) en[1], (Long) en[2]));
        
        Page<Object[]> result = boardRepository.searchPage(
        		pageRequseDTO.getType(),
        		pageRequseDTO.getKeyword(),
                pageRequseDTO.getPageable(Sort.by("bno").descending())
        );
        
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;
        return entityToDto((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = boardRepository.getById(boardDTO.getBno());
        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());
        boardRepository.save(board);
    }
}