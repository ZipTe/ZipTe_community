package org.gdg.zipte.api.controller.order.cart.request;

import lombok.Data;

import java.util.List;

@Data
public class CartDeleteRequest {

    // 멤버
    private Long memberId;

    // 아이템
    private List<CartItemRequest> items; // 여러개의 아이템을 삭제하도록

}
