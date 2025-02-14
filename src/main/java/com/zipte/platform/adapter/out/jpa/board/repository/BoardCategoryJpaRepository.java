package com.zipte.platform.adapter.out.jpa.board.repository;

import com.zipte.platform.adapter.out.jpa.board.BoardCategoryJpaEntity;
import com.zipte.platform.adapter.out.jpa.board.BoardJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardCategoryJpaRepository extends JpaRepository<BoardCategoryJpaEntity, Long> {

    @Query("""
    SELECT b
    FROM BoardCategoryJpaEntity bc
    JOIN bc.board b
    WHERE bc.category.id = :categoryId
""")
    Page<BoardJpaEntity> findBoardJpaEntitiesByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

}
