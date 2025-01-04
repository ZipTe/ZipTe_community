package org.gdg.zipte_gdg.api.service.shopping.productManger.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryNoChildrenResponse;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;

@Data
@Builder
@Getter
public class DiscountProductResponse {


    // 카테고리 관련 정보
    private CategoryNoChildrenResponse category;

    // 상품 관련 정보
    private ProductResponse product;

    // 가격 정보 추가
    private int discountRate;
    private int discountPrice;

    // 카테고리 빼고 생성자
    public static DiscountProductResponse from(ProductManager entity) {

        // 상품 관련
        Product product = entity.getProduct();
        ProductResponse prdouctResponse = ProductResponse.from(product);

        double discountPrice;
        // 할인율이 0일 경우, 원래 가격을 그대로 사용
        if (entity.getDiscountRate() == 0) {
            discountPrice = product.getPrice(); // 할인 없는 가격
        } else {
            // 할인율을 백분율로 계산하여 가격에 적용
            discountPrice = product.getPrice() - (product.getPrice() * (entity.getDiscountRate() / 100.0));
        }

        return DiscountProductResponse.builder()
                .product(prdouctResponse)
                .discountRate(entity.getDiscountRate())
                .discountPrice((int) discountPrice) // 할인된 가격 (소수점 버림)
                .build();
    }

}
