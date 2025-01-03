package org.gdg.zipte_gdg.domain.product.categorySet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategorySetRepository extends JpaRepository<CategorySet, Long> {

    @Query("""
    SELECT p, pi
    FROM CategorySet cs
    LEFT JOIN Product p ON cs.product.id = p.id
    LEFT JOIN ProductImage pi ON p.id = pi.product.id
    WHERE cs.category.id IN :categoryIds
""") Page<Object[]> findProductCategoriesByIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);

    @Query("""
    SELECT p, pi
    FROM CategorySet cs
    LEFT JOIN ProductManager p ON cs.product.id = p.product.id
    LEFT JOIN ProductImage pi ON p.product.id = pi.product.id
    WHERE cs.category.id IN :categoryIds
""") Page<Object[]> findProductManagerCategoriesByIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);


}
