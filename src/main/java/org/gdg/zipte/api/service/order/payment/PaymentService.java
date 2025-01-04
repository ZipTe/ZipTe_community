package org.gdg.zipte.api.service.order.payment;


import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.api.controller.order.toss.request.ConfirmPaymentRequest;
import org.gdg.zipte.api.service.order.payment.response.OrderResponse;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.api.service.order.payment.response.PaymentResponse;
import org.gdg.zipte.api.service.order.toss.response.TossPaymentResponse;

import java.net.http.HttpResponse;

public interface PaymentService {


    // 리액트에게 결과 리턴
    TossPaymentResponse savePayment(ConfirmPaymentRequest confirmPaymentRequest , HttpResponse<String> response) throws Exception;

    // 결제에서 상세 주문정보 얻어오기
    OrderResponse getDetails(String orderId) throws Exception;

    // 내 결제 목록 가져오기
    PageResponse<PaymentResponse> findMyPayments(Long memberId, PageRequest pageRequest);
}
