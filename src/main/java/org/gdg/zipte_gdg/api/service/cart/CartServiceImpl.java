package org.gdg.zipte_gdg.api.service.cart;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.cart.request.CartItemRequestDto;
import org.gdg.zipte_gdg.api.controller.cart.request.CartRequestDto;
import org.gdg.zipte_gdg.api.service.cart.response.CartResponseDto;
import org.gdg.zipte_gdg.domain.cart.Cart;
import org.gdg.zipte_gdg.domain.cart.CartItem;
import org.gdg.zipte_gdg.domain.cart.CartItemRepository;
import org.gdg.zipte_gdg.domain.cart.CartRepository;
import org.gdg.zipte_gdg.domain.product.Product;
import org.gdg.zipte_gdg.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponseDto setItem(CartRequestDto cartRequestDto) {
        Long memberId = cartRequestDto.getMemberId();
        Cart cart = cartRepository.findByMemberId(memberId);

        List<CartItemRequestDto> items = cartRequestDto.getItems();
        items.forEach(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            // 기존 CartItem이 있는지 확인
            CartItem existingCartItem = cart.getItems().stream()
                    .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingCartItem != null) {
                // 이미 존재하면 quantity만 증가
                existingCartItem.setQuantity(item.getQuantity());
                cartItemRepository.save(existingCartItem);
            } else {
                // 없으면 새로 추가
                CartItem newCartItem = CartItem.createCartItem(product, cart, item.getQuantity());
                CartItem savedCartItem = cartItemRepository.save(newCartItem);
                cart.addItem(savedCartItem);
            }
        });

        return EntityToDto(cart);
    }

    @Override
    public CartResponseDto removeItem(CartRequestDto cartRequestDto) {
        Long memberId = cartRequestDto.getMemberId();
        Cart cart = cartRepository.findByMemberId(memberId);

        List<CartItemRequestDto> items = cartRequestDto.getItems();
        items.forEach(item -> {

            // 장바구니에서 해당 상품 검색
            CartItem cartItem = cart.getItems().stream()
                    .filter(ci -> ci.getProduct().getId().equals(item.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Item not found in cart"));

            // 장바구니에서 상품 제거
            cart.removeItem(cartItem);
            cartItemRepository.delete(cartItem);
        });

        return EntityToDto(cart);
    }



    @Override
    public CartResponseDto getMyCart(Long memberId) {
        Cart cart = cartRepository.findByMemberId(memberId);

        return EntityToDto(cart);
    }
}
