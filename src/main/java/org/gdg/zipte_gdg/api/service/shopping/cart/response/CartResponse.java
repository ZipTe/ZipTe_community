package org.gdg.zipte_gdg.api.service.shopping.cart.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.domain.shopping.cart.Cart;
import org.gdg.zipte_gdg.domain.shopping.product.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CartResponse {

    // 오더
    private Long id;

    // 멤버
    private Long memberId;

    // 아이템
    private List<CartItemResponse> items; // 여러 상품 정보를 담는 리스트

    // 생성
    public static CartResponse of(Cart cart) {
        CartResponse responseDto = CartResponse.builder()
                .id(cart.getId())
                .memberId(cart.getMember().getId())
                .items(new ArrayList<>())
                .build();

        // 각 OrderItem에 대해 정보를 DTO로 변환하여 리스트에 추가
        cart.getItems().forEach(item -> {
            // 첫 번째 이미지를 가져오는 대신 이미지가 없으면 기본 이미지로 설정
            Product product = item.getProductManager().getProduct();

            String fileName = product.getProductImages().isEmpty() ? "default.jpeg" : product.getProductImages().get(0).getFileName();

            CartItemResponse itemDto = CartItemResponse.builder()
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
