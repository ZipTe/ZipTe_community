package com.eedo.project.zipte.representation.request.order;


import lombok.Data;

@Data
public class CartItemRequest {

    private Long productId;  // 상품 ID
    private int quantity;  // 상품 수량

}
