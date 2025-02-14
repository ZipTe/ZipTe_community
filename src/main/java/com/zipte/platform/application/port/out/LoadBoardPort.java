package com.zipte.platform.application.port.out;

import com.zipte.platform.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface LoadBoardPort {

    Optional<Board> findById(Long boardId);

//    List<Category> findRootCategories();

    Page<Board> findByCategoryId(Long categoryId, Pageable pageable);
}
