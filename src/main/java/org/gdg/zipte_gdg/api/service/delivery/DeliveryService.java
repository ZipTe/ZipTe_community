package org.gdg.zipte_gdg.api.service.delivery;

import org.gdg.zipte_gdg.api.controller.delivery.request.DeliveryUpdateDto;
import org.gdg.zipte_gdg.api.service.delivery.response.DeliveryResponseDto;
import org.gdg.zipte_gdg.domain.delivery.Delivery;

public interface DeliveryService {

    // 찾기
    DeliveryResponseDto findById(Long id);

    // 배송 상태 수정
    DeliveryResponseDto updateOne(DeliveryUpdateDto deliveryUpdateDto);

    default DeliveryResponseDto entityToDto(Delivery delivery) {
        return DeliveryResponseDto.builder()
                .id(delivery.getId())
                .city(delivery.getAddress().getCity())
                .streetAddress(delivery.getAddress().getStreetAddress())
                .zipCode(delivery.getAddress().getZipcode())
                .orderDesc(delivery.getOrderDesc())
                .deliveryDesc(delivery.getDeliveryDesc())
                .status(String.valueOf(delivery.getStatus()))
                .build();
    }

}
