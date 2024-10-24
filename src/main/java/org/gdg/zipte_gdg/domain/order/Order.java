package org.gdg.zipte_gdg.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.delivery.Delivery;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.product.OrderItem;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
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
    // 주문 생성
    public void createOrder(Member member,Delivery delivery,OrderItem orderItem) {
        this.member = member;
        this.delivery = delivery;
        this.orderItems.add(orderItem);
        this.status = OrderStatus.ORDER;

        // 비즈니스 로직
        this.delivery.setOrder(this);
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
