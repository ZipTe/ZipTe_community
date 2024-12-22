package org.gdg.zipte_gdg.api.service.cart;


import org.gdg.zipte_gdg.api.controller.cart.request.CartRequestDto;
import org.gdg.zipte_gdg.api.service.cart.response.CartItemResponseDto;
import org.gdg.zipte_gdg.api.service.cart.response.CartResponseDto;
import org.gdg.zipte_gdg.domain.cart.Cart;

import java.util.ArrayList;

public interface CartService {

    // 카트에 아이템 추가하기 (수량변경도 같이 동작)
    CartResponseDto setItem(CartRequestDto cartRequestDto);

    // 카트에 아이템 제거하기
    CartResponseDto removeItem(CartRequestDto cartRequestDto);

    // 카트 조회 하기
    CartResponseDto getMyCart(Long memberId);

    // 엔티티를 DTO로 변경
    default CartResponseDto EntityToDto(Cart cart) {
        CartResponseDto responseDto = CartResponseDto.builder()
                .id(cart.getId())
                .memberId(cart.getMember().getId())
                .items(new ArrayList<>())
                .build();

        // 각 OrderItem에 대해 정보를 DTO로 변환하여 리스트에 추가
        cart.getItems().forEach(item -> {
            CartItemResponseDto itemDto = CartItemResponseDto.builder()
                    .productId(item.getProduct().getId())
                    .productName(item.getProduct().getPname())
                    .quantity(item.getQuantity())
                    .price(item.getProduct().getPrice())
                    .totalPrice(item.getProduct().getPrice() * item.getQuantity())
                    .build();

            responseDto.getItems().add(itemDto); // 리스트에 추가
        });
        return responseDto;

    }
}
