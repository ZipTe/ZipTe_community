package org.gdg.zipte_gdg.domain.shopping.categorySet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategorySetRepository extends JpaRepository<CategorySet, Long> {

    @Query("SELECT p, pi FROM CategorySet sc LEFT JOIN Product p ON sc.product.id = p.id LEFT JOIN ProductImage pi ON p.id = pi.product.id WHERE sc.category.id = :id")
    Page<Object[]> findProductCategoriesById(@Param("id") Long id, Pageable pageable);
}
