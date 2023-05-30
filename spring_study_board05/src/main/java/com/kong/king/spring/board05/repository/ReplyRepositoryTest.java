package com.kong.king.spring.board05.repository;


import com.kong.king.spring.board05.entity.Board;
import com.kong.king.spring.board05.entity.Reply;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

//    @Test
//    void insertReply() {
//        IntStream.rangeClosed(1, 300).forEach(i -> {
//            long bno = (long)(Math.random()*100)+1;
//            
//            Board board = Board.builder().bno(bno).build();
//            
//            Reply reply = Reply.builder()
//                    .text("dab Reply....."+i)
//                    .board(board)
//                    .replyer("guest")
//                    .build();
//
//            replyRepository.save(reply);
//        });
//    }
//    @Test
//    void testRead1() {
//        Optional<Reply> result = replyRepository.findById(1L);
//
//        Reply reply = result.get();
//
//        System.out.println(reply);
//        System.out.println(reply.getBoard());
//        
//    }
//    
    @Test
    void testListByBoard() {
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(97L).build());
        replyList.forEach(System.out::println);
    }

}