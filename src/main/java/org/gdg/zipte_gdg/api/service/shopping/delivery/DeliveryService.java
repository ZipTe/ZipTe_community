package org.gdg.zipte_gdg.api.service.shopping.delivery;

import org.gdg.zipte_gdg.api.controller.shopping.delivery.request.DeliveryRequest;
import org.gdg.zipte_gdg.api.service.shopping.delivery.response.DeliveryResponse;

public interface DeliveryService {

    // 찾기
    DeliveryResponse findById(Long id);

    // 배송 주소지 수정
    DeliveryResponse updateAddress(DeliveryRequest deliveryRequest);

    // 배송 상태 수정
    DeliveryResponse updateStatus(DeliveryRequest deliveryRequest);

}
