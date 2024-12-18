package org.gdg.zipte_gdg.api.service.payment;


import org.gdg.zipte_gdg.api.controller.toss.request.ConfirmPaymentRequestDto;
import org.gdg.zipte_gdg.api.service.toss.response.TossPaymentResponseDto;
import org.gdg.zipte_gdg.domain.toss.TossPayment;

import java.net.http.HttpResponse;

public interface PaymentService {

    TossPaymentResponseDto savePayment(ConfirmPaymentRequestDto confirmPaymentRequest , HttpResponse<String> response) throws Exception;


    default TossPaymentResponseDto entityToDto(TossPayment tossPayment) throws Exception {
        return TossPaymentResponseDto.builder()
                .orderId(tossPayment.getTossOrderId())
                .amount(tossPayment.getTotalAmount())
                .build();

    }
}
