package com.eedo.project.zipte.representation.response;

import lombok.*;
import com.eedo.project.zipte.domain.payment.Payment;

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

