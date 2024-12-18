package org.gdg.zipte_gdg.domain.Toss;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentErrorResponse {

    private int code;
    private String message;
}
