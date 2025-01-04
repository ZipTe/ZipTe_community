package org.gdg.zipte.api.service.order.cart.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte.api.service.product.productManger.response.DiscountProductResponse;
import org.gdg.zipte.domain.order.cart.CartItem;
import org.gdg.zipte.domain.product.product.Product;
import org.gdg.zipte.domain.product.productManger.ProductManager;

import java.util.*;

@Data
@Builder
public class CartItemResponse {

    private DiscountProductResponse discountProduct;

    private int quantity;  // 상품 수량
    private int totalPrice; // 상품가격


    public static CartItemResponse from(CartItem cartItem) {

        ProductManager productManager = cartItem.getProductManager();
        Product product = productManager.getProduct();
        String fileName = product.getProductImages().isEmpty() ? "default.jpeg" : product.getProductImages().get(0).getFileName();

        DiscountProductResponse productResponse = DiscountProductResponse.from(productManager);
        productResponse.getProduct().setUploadFileNames(Collections.singletonList(fileName));

        return CartItemResponse.builder()
                .discountProduct(productResponse)
                .quantity(cartItem.getQuantity())
                .totalPrice(productResponse.getDiscountPrice() * cartItem.getQuantity())
                .build();
    }

    public static List<CartItemResponse> froms(List<CartItem> cartItems) {
        List<CartItemResponse> responseList = new ArrayList<>();

        cartItems.forEach(cartItem -> {
            responseList.add(CartItemResponse.from(cartItem));
        });

        return responseList;
    }
}

