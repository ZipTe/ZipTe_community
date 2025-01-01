package org.gdg.zipte_gdg.api.controller.shopping.cart.request;

import lombok.Data;

import java.util.List;

@Data
public class CartRequest {

    // 멤버
    private Long memberId;

    // 아이템
    private List<CartItemRequest> items; // 여러 상품 정보를 담는 리스트

}
