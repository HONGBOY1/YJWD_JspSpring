package com.kong.king.spring.board05.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kong.king.spring.board05.entity.Board;


public interface SearchBoardRepository {
    Board search1();
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}