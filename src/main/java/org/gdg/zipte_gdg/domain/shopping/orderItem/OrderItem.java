package org.gdg.zipte_gdg.domain.shopping.orderItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.shopping.order.Order;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;

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

    // 로직
    public void totalPrice() {
        this.price = this.productManager.getProduct().getPrice() * count;
    }

    public static OrderItem createOrderItem(ProductManager productManager, int count) {
        OrderItem orderItem = OrderItem.builder()
                .productManager(productManager)
                .count(count)
                .build();

        // 비즈니스 로직
        orderItem.totalPrice();
        return orderItem;
    }


    public void setOrder(Order order) {
        this.order = order;
    }

}
