package org.gdg.zipte_gdg.api.service.shopping.order;


import org.gdg.zipte_gdg.api.controller.shopping.order.request.OrderRequest;
import org.gdg.zipte_gdg.api.service.shopping.order.response.TossOrderResponse;

public interface OrderService {

    // 토스를 위한 주문 Response 생성
    TossOrderResponse order(OrderRequest orderRequest);

}

