package org.gdg.zipte_gdg.api.service.shopping.cart.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.domain.shopping.cart.Cart;
import org.gdg.zipte_gdg.domain.shopping.cart.CartItem;

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
    public static CartResponse from(Cart cart) {

        List<CartItem> cartItems = cart.getItems();
        List<CartItemResponse> cartItemList = CartItemResponse.froms(cartItems);

        return CartResponse.builder()
                .id(cart.getId())
                .memberId(cart.getMember().getId())
                .items(cartItemList)
                .build();
    }
}
