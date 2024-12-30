package org.gdg.zipte_gdg.domain.shopping.productManger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductManagerRepository extends JpaRepository<ProductManager, Long> {

    @Query("select pm from ProductManager pm where pm.product.id = :productId and pm.active=true")
    ProductManager findByProductId(@Param("productId") Long productId);

    @Query("select pm from ProductManager pm where pm.product.id = :productId and pm.description = 'basic'")
    ProductManager findBasicOne(@Param("productId") Long productId);


}
