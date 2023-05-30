package com.kong.king.spring.board05.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.kong.king.spring.board05.entity.Board;
import com.kong.king.spring.board05.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

//    @Test
//    void insertBoard() {
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            Member member = Member.builder().email("user"+i+"@king.kong.com").build();
//            Board board = Board.builder()
//                            .title("title..."+ (i%5) )
//                            .content("content..."+ (i%8))
//                            .writer(member)
//                            .build();
//            boardRepository.save(board);
//        });
//    }
//     @Transactional
//    @Test
//    void testRead1() {
//        Optional<Board> result = boardRepository.findById(100L);
//
//        if(result.isPresent()) {
//            Board board = result.get();
//
//            System.out.println(board);
//            System.out.println(board.getWriter());
//        }
//    }
    
//    @Test
//    public void testReadWithWriter() {
//        Object result = boardRepository.getBoardWithWriter(100L);
//        Object[] arr = (Object[]) result;
//        System.out.println(Arrays.toString(arr));
//    }
//    
//    @Test
//    public void testGetBoardWithReply() {
//        List<Object[]> result = boardRepository.getBoardWithReply(100L);
//        for(Object[] arr : result)
//            System.out.println(Arrays.toString(arr));
//    }
//    
//    @Test
//    public void testWithReplyCount() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
//        result.get().forEach(row -> {
//            Object[] arr = row;
//            System.out.println(Arrays.toString(arr));
//        });
//    }
//    
    @Test
    public void testGetBoardByBno() {
        Object result = boardRepository.getBoardByBno(100L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

}