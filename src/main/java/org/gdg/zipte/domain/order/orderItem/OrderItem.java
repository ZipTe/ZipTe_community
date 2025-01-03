package org.gdg.zipte.domain.order.orderItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.order.order.Order;
import org.gdg.zipte.domain.product.productManger.ProductManager;

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
    private ProductManager productManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;

    private int price;

    public void getTotalPrice() {
        double priceAfterDiscount = this.productManager.getProduct().getPrice() * (1 - this.productManager.getDiscountRate() / 100.0);
        this.price = (int) Math.round(priceAfterDiscount * count);
    }

    public static OrderItem of(ProductManager productManager, int count) {
        OrderItem orderItem = OrderItem.builder()
                .productManager(productManager)
                .count(count)
                .build();

        // 비즈니스 로직
        orderItem.getTotalPrice();
        return orderItem;
    }


    public void setOrder(Order order) {
        this.order = order;
    }

}
