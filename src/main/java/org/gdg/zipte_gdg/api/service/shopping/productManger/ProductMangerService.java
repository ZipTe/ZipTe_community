package org.gdg.zipte_gdg.api.service.shopping.productManger;

import org.gdg.zipte_gdg.api.controller.shopping.productManger.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;

public interface ProductMangerService {

    // 매니저 생성하기
    ProductManagerResponse create(ProductManagerRequest request);

    default ProductManagerResponse entityToDto(ProductManager productManager) {
        return ProductManagerResponse.builder()
                .product(entityToDto(productManager.getProduct()))
                .discountRate(productManager.getDiscountRate())
                .active(productManager.isActive())
                .saleStartDate(productManager.getSaleStartDate())
                .saleEndDate(productManager.getSaleEndDate())
                .couponCode(productManager.getCouponCode())
                .build();

    }

    //entity에서 전환
    default ProductResponseDto entityToDto(Product entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .pname(entity.getPname())
                .pdesc(entity.getPdesc())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }
}
