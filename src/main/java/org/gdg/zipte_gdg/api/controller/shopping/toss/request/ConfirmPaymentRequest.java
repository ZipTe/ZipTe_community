package org.gdg.zipte_gdg.api.controller.shopping.toss.request;

import lombok.Data;

@Data
public class ConfirmPaymentRequest {

    // 리액트에서 받는 DTO객체

    private String paymentKey;
    private String orderId;
    private int amount;

}
