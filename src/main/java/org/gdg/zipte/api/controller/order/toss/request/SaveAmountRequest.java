package org.gdg.zipte.api.controller.order.toss.request;

import lombok.Data;

@Data
public class SaveAmountRequest {

    private String orderId;
    private int amount;

}
