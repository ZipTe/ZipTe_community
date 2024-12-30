package org.gdg.zipte_gdg.domain.shopping.productManger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductManagerRepository extends JpaRepository<ProductManager, Long> {

    ProductManager findByProductId(Long productId);
}
