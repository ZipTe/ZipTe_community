package org.gdg.zipte_gdg.api.controller.cart.request;

import lombok.Data;

import java.util.List;

@Data
public class CartRequestDto {

    // 멤버
    private Long memberId;

    // 아이템
    private List<CartItemRequestDto> items; // 여러 상품 정보를 담는 리스트

}
