package com.eedo.project.zipte.infrastructure.out.pg.toss.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.payment.Payment;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TossPaymentResponse {

        private int amount; // 가격 정보
        private String orderId; // 주문 Id

        public static TossPaymentResponse from(Payment payment) throws Exception {
                return TossPaymentResponse.builder()
                        .orderId(payment.getTossOrderId())
                        .amount(payment.getTotalAmount())
                        .build();

        }
}
