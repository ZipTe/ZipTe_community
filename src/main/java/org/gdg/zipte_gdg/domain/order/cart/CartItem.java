package org.gdg.zipte_gdg.domain.order.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.product.productManger.ProductManager;

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
    private ProductManager productManager;

    private int quantity;

    // CartItem 생성 로직
    public static CartItem of(ProductManager productManager, Cart cart, int quantity) {
        return CartItem.builder()
                .productManager(productManager)
                .cart(cart)
                .quantity(quantity)
                .build();
    }

    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

//    public void deleteCart() {
//        this.cart = null;
//    }

}
