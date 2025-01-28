package com.eedo.project.zipte.representation.request.toss;

import lombok.Data;

@Data
public class SaveAmountRequest {

    private String orderId;
    private int amount;

}
