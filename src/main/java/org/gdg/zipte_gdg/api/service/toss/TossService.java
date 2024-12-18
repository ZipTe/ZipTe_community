package org.gdg.zipte_gdg.api.service.toss;

import org.gdg.zipte_gdg.api.controller.toss.request.ConfirmPaymentRequestDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;

@Service
public interface TossService {

    // 토스에게 요청하는 서비스
    HttpResponse<String> requestConfirm(ConfirmPaymentRequestDto confirmPaymentRequestDto) throws IOException, InterruptedException;
    HttpResponse<String> requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException;


}
