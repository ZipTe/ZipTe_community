package org.gdg.zipte_gdg.domain.Toss;


import jakarta.persistence.*;
import lombok.*;
import org.gdg.zipte_gdg.domain.order.Order;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayment {

    @Id
    byte[] paymentId;

    @Column(nullable = false, unique = true)
    String tossPaymentKey;

    // 토스내부에서 관리하는 별도의 orderId가 존재함
    @Column(nullable = false)
    String tossOrderId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    long totalAmount;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    TossPaymentMethod tossPaymentMethod;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    TossPaymentStatus tossPaymentStatus;

    @Column(nullable = false)
    LocalDateTime requestedAt;

    LocalDateTime approvedAt;

    public static TossPayment createPayment(String tossPaymentKey, String tossOrderId, Order order) {
        return TossPayment.builder()
                .tossPaymentKey(tossPaymentKey)
                .tossOrderId(tossOrderId)
                .build();

    }
}