package com.eedo.project.zipte.infrastructure.out.persistence.jpa.product;


import com.eedo.project.zipte.domain.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {


}
