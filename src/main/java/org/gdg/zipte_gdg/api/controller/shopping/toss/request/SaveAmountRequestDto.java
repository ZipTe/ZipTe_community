package org.gdg.zipte_gdg.api.controller.shopping.toss.request;

import lombok.Data;

@Data
public class SaveAmountRequestDto {

    private String orderId;
    private int amount;

}
