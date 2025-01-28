package org.gdg.zipte.api.service.order.delivery;

import org.gdg.zipte.api.controller.order.delivery.request.DeliveryRequest;
import org.gdg.zipte.api.service.order.delivery.response.DeliveryResponse;

public interface DeliveryService {

    // 배송 주소지 수정
    DeliveryResponse updateAddress(DeliveryRequest deliveryRequest);

    // 배송 상태 수정
    DeliveryResponse updateStatus(DeliveryRequest deliveryRequest);

}
