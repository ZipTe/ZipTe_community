package com.eedo.project.zipte.infrastructure.out.persistence.jpa.product;

import com.eedo.project.zipte.domain.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    List<Category> findRootCategories();

    Boolean existsByCode(String code);
}
