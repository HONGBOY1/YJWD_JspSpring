package com.kong.king.spring.board05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kong.king.spring.board05.entity.Board;
import com.kong.king.spring.board05.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(Long bno);
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}