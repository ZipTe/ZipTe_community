package org.gdg.zipte_gdg.api.service.shopping.toss.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TossPaymentResponseDto {

        private int amount; // 가격 정보
        private String orderId; // 주문 Id
}
