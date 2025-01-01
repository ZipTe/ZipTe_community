package org.gdg.zipte_gdg.api.service.shopping.productManger;

import org.gdg.zipte_gdg.api.controller.shopping.productManger.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.DiscountProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;

public interface ProductMangerService {

    // 매니저 생성하기
    ProductManagerResponse create(ProductManagerRequest request);

    // 할인된 금액으로 조회
    PageResponseDto<DiscountProductResponse> findAll(PageRequestDto pageRequestDto);

    // 단일 상품 조회
    DiscountProductResponse findById(Long id);


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

    default DiscountProductResponse entityToDiscountDto(ProductManager entity) {
        Product product = entity.getProduct();
        double discountPrice;

        // 할인율이 0일 경우, 원래 가격을 그대로 사용
        if (entity.getDiscountRate() == 0) {
            discountPrice = product.getPrice(); // 할인 없는 가격
        } else {
            // 할인율을 백분율로 계산하여 가격에 적용
            discountPrice = product.getPrice() - (product.getPrice() * (entity.getDiscountRate() / 100.0));
        }

        return DiscountProductResponse.builder()
                .id(product.getId())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .stock(product.getStock())
                .price(product.getPrice())
                .discountRate(entity.getDiscountRate())
                .discountPrice((int) discountPrice) // 할인된 가격 (소수점 버림)
                .build();
    }


}
