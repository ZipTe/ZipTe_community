package com.eedo.project.zipte.application.service.order;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.zipte.representation.request.order.CartDeleteRequest;
import com.eedo.project.zipte.representation.request.order.CartItemRequest;
import com.eedo.project.zipte.representation.request.order.CartRequest;
import com.eedo.project.zipte.application.port.in.CartService;
import com.eedo.project.zipte.representation.response.CartResponse;
import com.eedo.project.zipte.domain.order.Cart;
import com.eedo.project.zipte.domain.order.CartItem;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.order.CartItemRepository;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.order.CartRepository;
import com.eedo.project.zipte.domain.product.ProductManager;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.product.ProductManagerRepository;
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
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저의 장바구니가 없습니다."));

        CartItemRequest item = cartRequest.getItem();
        ProductManager productmanger = productManagerRepository.findByProductId(item.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("해당 상품 매니저가 없습니다"));

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
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저의 장바구니가 없습니다"));

        List<CartItemRequest> items = cartRequest.getItems();
        items.forEach(item -> {

            // 장바구니에서 해당 상품 검색
            CartItem cartItem = cart.getItems().stream()
                    .filter(ci -> ci.getProductManager().getProduct().getId().equals(item.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("장바구니에서 아이템이 존재하지 않습니다."));

            // 장바구니에서 상품 제거
            cart.removeItem(cartItem);
            cartItemRepository.delete(cartItem);
        });

        return CartResponse.from(cart);
    }

    @Override
    public CartResponse getMyCart(Long memberId) {
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException("장바구니에서 아이템이 존재하지 않습니다."));

        return CartResponse.from(cart);
    }
}
