package com.eedo.project.zipte.representation.request.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemRequest {

    private Long productId;  // 상품 ID
    private int count;  // 상품 수량

}
