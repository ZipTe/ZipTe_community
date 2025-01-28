package com.eedo.project.zipte.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.user.Member;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "tossOrder_id", nullable = false, unique = true, updatable = false)
    @Builder.Default
    private String tossOrderId = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    @CreatedDate
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 비즈니스 로직 추가
    public static Order of(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = Order.builder()
                .member(member)
                .delivery(delivery)
                .orderDate(LocalDateTime.now())
                .orderItems(orderItems)
                .status(OrderStatus.ORDER)
                .build();

        delivery.setOrder(order);
        orderItems.forEach(orderItem -> orderItem.setOrder(order));

        return order;
    }

    // 주문 완료
    public void complete() {
        this.status = OrderStatus.COMPLETE;
        this.delivery.deliveryComplete();
    }

    // 주문취소
    public void cancel() {
        this.status = OrderStatus.CANCEL;
        this.delivery.deliveryCancel();
    }

    // 배송지 업데이트
    public void update(Delivery new_delivery) {
        this.delivery = new_delivery;
        this.delivery.update(new_delivery.getAddress());
    }
}
