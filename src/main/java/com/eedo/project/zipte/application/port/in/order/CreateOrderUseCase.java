package com.eedo.project.zipte.application.port.in.order;


import com.eedo.project.zipte.domain.order.Order;
import com.eedo.project.zipte.representation.request.order.OrderRequest;

public interface CreateOrderUseCase {

    // 토스를 위한 가주문 Response 생성
    Order order(OrderRequest orderRequest);

}

