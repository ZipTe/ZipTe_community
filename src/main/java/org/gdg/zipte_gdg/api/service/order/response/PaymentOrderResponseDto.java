package org.gdg.zipte_gdg.api.service.order.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class PaymentOrderResponseDto {

    // 오더
    private String orderId;

    // 아이템
    private String orderName;
    private String amount;

    // 멤버
    private String customerName;
    private String customerEmail;
    private String customerMobilePhone;


}
