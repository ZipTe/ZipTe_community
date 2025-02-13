package com.zipte.platform.adapter.out.jpa.board.repository;

import com.zipte.platform.adapter.out.jpa.board.CategoryJpaEntity;
import com.zipte.platform.domain.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    @Query("SELECT bc FROM CategoryJpaEntity bc WHERE bc.parent IS NULL")
    List<CategoryJpaEntity> findRootCategories();

    boolean existsByCode(String code);
}
