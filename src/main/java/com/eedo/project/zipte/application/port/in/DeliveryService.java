package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.representation.request.order.DeliveryRequest;
import com.eedo.project.zipte.representation.response.DeliveryResponse;

public interface DeliveryService {

    // 배송 주소지 수정
    DeliveryResponse updateAddress(DeliveryRequest deliveryRequest);

    // 배송 상태 수정
    DeliveryResponse updateStatus(DeliveryRequest deliveryRequest);

}
