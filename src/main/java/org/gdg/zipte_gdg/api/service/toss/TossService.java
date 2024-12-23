package org.gdg.zipte_gdg.api.service.toss;

import org.gdg.zipte_gdg.api.controller.toss.request.ConfirmPaymentRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;

@Service
public interface TossService {

    // 결제 검증 요청
    HttpResponse<String> requestConfirm(ConfirmPaymentRequestDto confirmPaymentRequestDto) throws IOException, InterruptedException;

    // 결제 취소 요청
    HttpResponse<String> requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException;
}
