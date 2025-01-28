package com.eedo.project.zipte.application.port.in;


import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.zipte.representation.request.toss.ConfirmPaymentRequest;
import com.eedo.project.zipte.representation.response.OrderResponse;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.zipte.representation.response.PaymentResponse;
import com.eedo.project.zipte.infrastructure.out.pg.toss.response.TossPaymentResponse;

import java.net.http.HttpResponse;

public interface PaymentService {


    // 리액트에게 결과 리턴
    TossPaymentResponse savePayment(ConfirmPaymentRequest confirmPaymentRequest , HttpResponse<String> response) throws Exception;

    // 결제에서 상세 주문정보 얻어오기
    OrderResponse getDetails(String orderId) throws Exception;

    // 내 결제 목록 가져오기
    PageResponse<PaymentResponse> findMyPayments(Long memberId, PageRequest pageRequest);
}
