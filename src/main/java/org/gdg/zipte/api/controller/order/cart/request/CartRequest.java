package org.gdg.zipte.api.controller.order.cart.request;

import lombok.Data;

@Data
public class CartRequest {

    // 멤버
    private Long memberId;

    // 아이템
    private CartItemRequest item; // 여러 상품 정보를 담는 리스트

}
