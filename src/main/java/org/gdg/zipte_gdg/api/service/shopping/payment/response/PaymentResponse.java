package org.gdg.zipte_gdg.api.service.shopping.payment.response;

import lombok.*;
import org.gdg.zipte_gdg.domain.shopping.payment.Payment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PaymentResponse {

        private String tossOrderId;
        private int totalAmount;
        private String tossOrderName;
        private String requestedAt;

        // 생성자
        public static PaymentResponse from(Payment payment) {
                return PaymentResponse.builder()
                        .tossOrderId(payment.getTossOrderId())
                        .tossOrderName(payment.getTossOrderName())
                        .totalAmount(payment.getTotalAmount())
                        .requestedAt(payment.getRequestedAt())
                        .build();
        }

        // 여러 개
        public static List<PaymentResponse> froms(List<Payment> payments) {
                return payments.stream()
                        .map(PaymentResponse::from) // 클래스 이름 사용
                        .collect(Collectors.toList());
        }

}

