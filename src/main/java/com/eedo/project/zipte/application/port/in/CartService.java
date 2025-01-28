package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.representation.request.order.CartDeleteRequest;
import com.eedo.project.zipte.representation.request.order.CartRequest;
import com.eedo.project.zipte.representation.response.CartResponse;

public interface CartService {

    // 카트에 아이템 추가하기 (수량변경도 같이 동작)
    CartResponse setItem(CartRequest cartRequest);

    // 카트에 아이템 제거하기
    CartResponse removeItem(CartDeleteRequest cartRequest);

    // 카트 조회 하기
    CartResponse getMyCart(Long memberId);

}
