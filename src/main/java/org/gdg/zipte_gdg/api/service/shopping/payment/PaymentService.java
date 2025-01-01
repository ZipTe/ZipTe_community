package org.gdg.zipte_gdg.api.service.shopping.payment;


import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.shopping.toss.request.ConfirmPaymentRequest;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.OrderResponse;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.PaymentResponse;
import org.gdg.zipte_gdg.api.service.shopping.toss.response.TossPaymentResponse;

import java.net.http.HttpResponse;

public interface PaymentService {


    // 리액트에게 결과 리턴
    TossPaymentResponse savePayment(ConfirmPaymentRequest confirmPaymentRequest , HttpResponse<String> response) throws Exception;

    // 결제에서 상세 주문정보 얻어오기
    OrderResponse getDetails(String orderId) throws Exception;

    // 내 결제 목록 가져오기
    PageResponseDto<PaymentResponse> findMyPayments(Long memberId, PageRequestDto pageRequestDto);
}
