package org.gdg.zipte_gdg.api.service.shopping.payment.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.domain.shopping.orderItem.OrderItem;

@Data
@Builder
public class OrderItemResponse {

    private Long productId;  // 상품 ID
    private String productName;  // 상품 이름
    private int count;  // 상품 수량
    private int price; // 상품가격


    // 생성자
    public static OrderItemResponse of(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .productId(orderItem.getProductManager().getProduct().getId())
                .productName(orderItem.getProductManager().getProduct().getPname())
                .count(orderItem.getCount())
                .price(orderItem.getPrice())
                .build();

    }
}

