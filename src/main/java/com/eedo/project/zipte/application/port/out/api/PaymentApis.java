package com.eedo.project.zipte.application.port.out.api;

import com.eedo.project.zipte.representation.request.toss.ConfirmPaymentRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;

@Service
public interface PaymentApis {

    // 결제 검증 요청
    HttpResponse<String> requestConfirm(ConfirmPaymentRequest confirmPaymentRequest) throws IOException, InterruptedException;

    // 결제 취소 요청
    HttpResponse<String> requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException;
}
