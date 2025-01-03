package org.gdg.zipte.api.controller.order.cart.request;


import lombok.Data;

@Data
public class CartItemRequest {

    private Long productId;  // 상품 ID
    private int quantity;  // 상품 수량

}
