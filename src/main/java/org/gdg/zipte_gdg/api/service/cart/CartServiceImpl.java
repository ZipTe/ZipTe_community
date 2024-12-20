package org.gdg.zipte_gdg.api.service.cart;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.cart.request.CartItemRequestDto;
import org.gdg.zipte_gdg.api.controller.cart.request.CartRequestDto;
import org.gdg.zipte_gdg.api.controller.order.request.OrderItemRequestDto;
import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.service.cart.response.CartResponseDto;
import org.gdg.zipte_gdg.domain.cart.Cart;
import org.gdg.zipte_gdg.domain.cart.CartItem;
import org.gdg.zipte_gdg.domain.cart.CartItemRepository;
import org.gdg.zipte_gdg.domain.cart.CartRepository;
import org.gdg.zipte_gdg.domain.product.Product;
import org.gdg.zipte_gdg.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
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

    @Override
    @Transactional
    public OrderRequestDto orderCartItem(CartRequestDto cartRequestDto) {
        Cart cart = cartRepository.findByMemberId(cartRequestDto.getMemberId());

        // 주문할 상품 리스트 (CartItemRequestDto로 전달된 리스트)
        List<CartItemRequestDto> itemsToOrder = cartRequestDto.getItems();
        List<OrderItemRequestDto> orderItemRequestDto = new ArrayList<>();

        // 선택된 각 상품을 주문 항목으로 변환하고 장바구니에서 제거
        itemsToOrder.forEach(item -> {
            // 장바구니에서 해당 상품 검색
            CartItem cartItem = cart.getItems().stream()
                    .filter(ci -> ci.getProduct().getId().equals(item.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Item not found in cart"));

            // 선택된 상품의 수량과 주문할 수 있는 수량이 맞는지 체크
            if (cartItem.getQuantity() < item.getQuantity()) {
                throw new IllegalArgumentException("Insufficient quantity in cart for product: " + cartItem.getProduct().getPname());
            }

            // 주문 항목을 DTO로 변환하여 리스트에 담기

            OrderItemRequestDto build = OrderItemRequestDto.builder()
                    .productId(item.getProductId())
                    .count(item.getQuantity())
                    .build();

            orderItemRequestDto.add(build);
            // 장바구니에서 삭제는 오더 이후에 추가로 호출하도록 하는걸로 한다.
//            cart.removeItem(cartItem);
        });

        // OrderRequestDto 생성 (이 DTO는 실제로 주문을 생성할 때 사용할 수 있음)
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setMemberId(cartRequestDto.getMemberId());
        orderRequestDto.setItems(orderItemRequestDto);

        // 오더 리퀘스트 DTO로 변환하여 반환
        return orderRequestDto;
    }
}
