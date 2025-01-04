package org.gdg.zipte.api.service.order.order;


import org.gdg.zipte.api.controller.order.order.request.OrderRequest;
import org.gdg.zipte.api.service.order.order.response.TossOrderResponse;

public interface OrderService {

    // 토스를 위한 주문 Response 생성
    TossOrderResponse order(OrderRequest orderRequest);

}

