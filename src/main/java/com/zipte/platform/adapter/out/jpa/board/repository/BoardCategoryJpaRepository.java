package com.zipte.platform.adapter.out.jpa.board.repository;

import com.zipte.platform.adapter.out.jpa.board.BoardCategoryJpaEntity;
import com.zipte.platform.domain.board.BoardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardCategoryJpaRepository extends JpaRepository<BoardCategory, Long> {

    @Query("""
    SELECT bs
    FROM BoardCategoryJpaEntity bs
    WHERE bs.category.id IN :categoryIds
""") Page<BoardCategoryJpaEntity> findProductCategoriesByIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);

    @Query("select bc from BoardCategoryJpaEntity bc where bc.board.id = :boardId")
    Optional<BoardCategoryJpaEntity> findByBoardId(@Param("boardId") Long boardId);

}
