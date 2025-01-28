package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import com.eedo.project.zipte.domain.product.ProductManager;

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
