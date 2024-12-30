package org.gdg.zipte_gdg.api.service.shopping.productManger;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.productManger.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.product.ProductRepository;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductMangerServiceImpl implements ProductMangerService {

    private final ProductManagerRepository productManagerRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductManagerResponse create(ProductManagerRequest request) {

        Product product = productRepository.findById(request.getProductId()).orElseThrow();

        // 기존 매니저가 있을 경우 처리
        ProductManager existingManager = productManagerRepository.findByProductId(product.getId());
        if (existingManager != null) {
            existingManager.setActive(false);
            productManagerRepository.save(existingManager);  // 기존 매니저 업데이트
        }

        ProductManager productManager = ProductManager.of(product, request.getDiscountRate(), request.isActive(),request.getDescription());
        productManager.setSaleStartDate(request.getSaleStartDate());
        productManager.setSaleEndDate(request.getSaleEndDate());
        productManager.setCouponCode(request.getCouponCode());
        ProductManager manager = productManagerRepository.save(productManager);

        return entityToDto(manager);
    }
}
