package org.gdg.zipte_gdg.api.service.payment.response;

import lombok.*;

@Data
@Builder
public class PaymentResponseDto {

        private String tossOrderId;
        private int totalAmount;
        private String tossOrderName;
        private String requestedAt;
}

