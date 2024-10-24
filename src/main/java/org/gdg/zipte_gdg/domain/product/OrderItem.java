package org.gdg.zipte_gdg.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.order.Order;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;

    private int price;

    // 로직
    public int totalPrice() {
        return price * count;
    }

    public static OrderItem createOrderItem(Product product, int count, int price) {
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .count(count)
                .price(price)
                .build();

        // 비즈니스 로직
        product.removeStock(count);

        return orderItem;
    }

    public void cancelOrderItem(int count) {
        this.product.addStock(count);
    }
}
