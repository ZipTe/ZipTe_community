package com.eedo.project.zipte.application.port.in;


import com.eedo.project.zipte.representation.request.order.OrderRequest;
import com.eedo.project.zipte.representation.response.TossOrderResponse;

public interface OrderService {

    // 토스를 위한 주문 Response 생성
    TossOrderResponse order(OrderRequest orderRequest);

}

