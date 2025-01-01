package org.gdg.zipte_gdg.api.service.shopping.cart;

import org.gdg.zipte_gdg.api.controller.shopping.cart.request.CartRequest;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartResponse;

public interface CartService {

    // 카트에 아이템 추가하기 (수량변경도 같이 동작)
    CartResponse setItem(CartRequest cartRequest);

    // 카트에 아이템 제거하기
    CartResponse removeItem(CartRequest cartRequest);

    // 카트 조회 하기
    CartResponse getMyCart(Long memberId);

}
