package com.zipte.platform.adapter.out.jpa.board;

import com.zipte.platform.domain.board.BoardCategorySet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardCategorySetRepository extends JpaRepository<BoardCategorySet, Long> {

    @Query("""
    SELECT bs, bi
    FROM BoardCategorySet bs
    LEFT JOIN BoardImage bi ON bs.board.id = bi.board.id
    WHERE bs.category.id IN :categoryIds
""") Page<Object[]> findProductCategoriesByIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);

    @Query("select bc from BoardCategorySet bc where bc.board.id = :boardId")
    Optional<BoardCategorySet> findByBoardId(@Param("boardId") Long boardId);

}
