package org.gdg.zipte_gdg.api.service.shopping.cart;


import org.gdg.zipte_gdg.api.controller.shopping.cart.request.CartRequestDto;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartItemResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartResponseDto;
import org.gdg.zipte_gdg.domain.shopping.cart.Cart;
import org.gdg.zipte_gdg.domain.shopping.product.Product;

import java.util.ArrayList;

public interface CartService {

    // 카트에 아이템 추가하기 (수량변경도 같이 동작)
    CartResponseDto setItem(CartRequestDto cartRequestDto);

    // 카트에 아이템 제거하기
    CartResponseDto removeItem(CartRequestDto cartRequestDto);

    // 카트 조회 하기
    CartResponseDto getMyCart(Long memberId);

    default CartResponseDto EntityToDto(Cart cart) {
        CartResponseDto responseDto = CartResponseDto.builder()
                .id(cart.getId())
                .memberId(cart.getMember().getId())
                .items(new ArrayList<>())
                .build();

        // 각 OrderItem에 대해 정보를 DTO로 변환하여 리스트에 추가
        cart.getItems().forEach(item -> {
            // 첫 번째 이미지를 가져오는 대신 이미지가 없으면 기본 이미지로 설정
            Product product = item.getProductManager().getProduct();

            String fileName = product.getProductImages().isEmpty() ? "default.jpeg" : product.getProductImages().get(0).getFileName();

            CartItemResponseDto itemDto = CartItemResponseDto.builder()
                    .productId(product.getId())
                    .productName(product.getPname())
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .totalPrice(product.getPrice() * item.getQuantity())
                    .productImage(fileName) // 이미지가 없으면 기본 이미지 설정
                    .build();

            responseDto.getItems().add(itemDto); // 리스트에 추가
        });

        return responseDto;
    }

}
