package org.gdg.zipte_gdg.api.service.cart.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponseDto {

    // 오더
    private Long id;

    // 멤버
    private Long memberId;

    // 아이템
    private List<CartItemResponseDto> items; // 여러 상품 정보를 담는 리스트


}
