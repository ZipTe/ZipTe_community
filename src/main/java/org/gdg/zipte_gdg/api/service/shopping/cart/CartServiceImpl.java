package org.gdg.zipte_gdg.api.service.shopping.cart;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.shopping.cart.request.CartDeleteRequest;
import org.gdg.zipte_gdg.api.controller.shopping.cart.request.CartItemRequest;
import org.gdg.zipte_gdg.api.controller.shopping.cart.request.CartRequest;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartResponse;
import org.gdg.zipte_gdg.domain.shopping.cart.Cart;
import org.gdg.zipte_gdg.domain.shopping.cart.CartItem;
import org.gdg.zipte_gdg.domain.shopping.cart.CartItemRepository;
import org.gdg.zipte_gdg.domain.shopping.cart.CartRepository;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductManagerRepository productManagerRepository;

    @Override
    public CartResponse setItem(CartRequest cartRequest) {
        Long memberId = cartRequest.getMemberId();
        Cart cart = cartRepository.findByMemberId(memberId);

        CartItemRequest item = cartRequest.getItem();
        ProductManager productmanger = productManagerRepository.findByProductId(item.getProductId());

        // 기존 CartItem이 있는지 확인
        CartItem existingCartItem = cart.getItems().stream()
                .filter(cartItem -> cartItem.getProductManager().getProduct().getId().equals(productmanger.getProduct().getId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            // 이미 존재하면 quantity만 증가
            existingCartItem.setQuantity(item.getQuantity());
            cartItemRepository.save(existingCartItem);
        } else {
            // 없으면 새로 추가
            CartItem newCartItem = CartItem.of(productmanger, cart, item.getQuantity());
            CartItem savedCartItem = cartItemRepository.save(newCartItem);
            cart.addItem(savedCartItem);
        }

        return CartResponse.from(cart);
    }

    @Override
    public CartResponse removeItem(CartDeleteRequest cartRequest) {
        Long memberId = cartRequest.getMemberId();
        Cart cart = cartRepository.findByMemberId(memberId);

        List<CartItemRequest> items = cartRequest.getItems();
        items.forEach(item -> {

            // 장바구니에서 해당 상품 검색
            CartItem cartItem = cart.getItems().stream()
                    .filter(ci -> ci.getProductManager().getProduct().getId().equals(item.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Item not found in cart"));

            // 장바구니에서 상품 제거
            cart.removeItem(cartItem);
            cartItemRepository.delete(cartItem);
        });

        return CartResponse.from(cart);
    }

    @Override
    public CartResponse getMyCart(Long memberId) {
        Cart cart = cartRepository.findByMemberId(memberId);

        return CartResponse.from(cart);
    }
}
