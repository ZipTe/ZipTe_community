package org.gdg.zipte_gdg.domain.eCommerce.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.eCommerce.product.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    // CartItem 생성 로직
    public static CartItem createCartItem(Product product,Cart cart, int quantity) {
        return CartItem.builder()
                .product(product)
                .cart(cart)
                .quantity(quantity)
                .build();
    }

//    public void addQuantity(int quantity) {
//        this.quantity+=quantity;
//    }
//
//    public void removeQuantity(int quantity) {
//        this.quantity-=quantity;
//    }

    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

//    public void deleteCart() {
//        this.cart = null;
//    }

}
