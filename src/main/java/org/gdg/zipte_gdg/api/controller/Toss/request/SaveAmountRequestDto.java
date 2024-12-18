package org.gdg.zipte_gdg.api.controller.Toss.request;

import lombok.Data;

@Data
public class SaveAmountRequestDto {

    private String orderId;
    private int amount;

}
