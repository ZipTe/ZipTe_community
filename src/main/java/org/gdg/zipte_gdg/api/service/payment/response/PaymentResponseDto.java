package org.gdg.zipte_gdg.api.service.payment.response;

import lombok.*;
import org.gdg.zipte_gdg.domain.order.Order;

@Data
@Builder
public class PaymentResponseDto {

        private String tossOrderId;
        private int totalAmount;
        private String tossOrderName;
        private String requestedAt;
}

