package org.gdg.zipte_gdg.api.service.toss.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TossPaymentErrorResponse {

    private int code;
    private String message;
}
