package org.gdg.zipte_gdg.api.service.order.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponseDto {

    private Long productId;  // 상품 ID
    private String productName;  // 상품 이름
    private int count;  // 상품 수량
    private int price; // 상품가격

}

