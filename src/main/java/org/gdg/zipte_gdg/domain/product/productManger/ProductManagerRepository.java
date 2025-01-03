package org.gdg.zipte_gdg.domain.product.productManger;

import org.gdg.zipte_gdg.domain.product.categorySet.CategorySet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductManagerRepository extends JpaRepository<ProductManager, Long> {

    @Query("select pm from ProductManager pm where pm.product.id = :productId and pm.active=true")
    ProductManager findByProductId(@Param("productId") Long productId);

    @Query("select cs from ProductManager pm left JOIN CategorySet cs on pm.product.id = cs.product.id where pm.product.id = :productId and pm.active=true")
    CategorySet findCategoryByProductId(@Param("productId") Long productId);

    @Query("select pm from ProductManager pm where pm.product.id = :productId and pm.description = 'basic'")
    ProductManager findBasicOne(@Param("productId") Long productId);

    @Query("SELECT pm, pi FROM ProductManager pm LEFT JOIN ProductImage pi ON pm.product.id = pi.product.id WHERE (pi.ord = 0 OR pi.ord IS NULL) AND pm.active = true")
    Page<Object[]> selectList(Pageable pageable);


}
