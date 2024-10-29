package org.gdg.zipte_gdg.domain.orderItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.order.Order;
import org.gdg.zipte_gdg.domain.product.Product;

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
    public void totalPrice() {
        this.price = this.product.getPrice() * count;
    }

    public static OrderItem createOrderItem(Product product, int count) {
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .count(count)
                .build();

        // 비즈니스 로직
        product.removeStock(count);
        orderItem.totalPrice();
        return orderItem;
    }

    public void cancelOrderItem(int count) {
        this.product.addStock(count);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
