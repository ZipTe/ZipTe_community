package org.gdg.zipte_gdg.api.service.toss.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TossPaymentResponseDto {
        private String payType; // 결제 타입 - 카드/현금/포인트
        private int amount; // 가격 정보
        private String orderName; // 주문명
        private String orderId; // 주문 Id
        private String customerEmail; // 고객 이메일
        private String customerName; // 고객 이름
        private String failReason; // 실패 이유
        private boolean cancelYN; // 취소 YN
        private String cancelReason; // 취소 이유
        private String createdAt; // 결제가 이루어진 시간

}
