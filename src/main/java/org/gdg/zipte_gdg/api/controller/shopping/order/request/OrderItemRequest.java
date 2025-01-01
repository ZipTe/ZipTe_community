package org.gdg.zipte_gdg.api.controller.shopping.order.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemRequest {

    private Long productId;  // 상품 ID
    private int count;  // 상품 수량

}
