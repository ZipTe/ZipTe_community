package org.gdg.zipte_gdg.api.service.shopping.productManger.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;

import java.util.Date;

@Data
@Builder
@Getter
public class ProductManagerResponse {

    private ProductResponse product;

    private int discountRate;
    private String couponCode;
    private boolean active;
    private Date saleStartDate;
    private Date saleEndDate;

    public static ProductManagerResponse from(ProductManager productManager) {
        return ProductManagerResponse.builder()
                .product(ProductResponse.from(productManager.getProduct()))
                .discountRate(productManager.getDiscountRate())
                .active(productManager.isActive())
                .saleStartDate(productManager.getSaleStartDate())
                .saleEndDate(productManager.getSaleEndDate())
                .couponCode(productManager.getCouponCode())
                .build();

    }
}
