package org.gdg.zipte_gdg.api.service.toss;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;
import org.gdg.zipte_gdg.api.controller.Toss.request.ConfirmPaymentRequestDto;
import org.gdg.zipte_gdg.api.service.toss.response.TossPaymentResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;


@Service
public interface TossService {

    HttpResponse<String> requestConfirm(ConfirmPaymentRequestDto confirmPaymentRequestDto) throws IOException, InterruptedException;
    HttpResponse<String> requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException;


}
