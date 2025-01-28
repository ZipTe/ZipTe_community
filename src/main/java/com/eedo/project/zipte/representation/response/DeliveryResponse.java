package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;
import com.eedo.project.zipte.domain.order.Delivery;
import com.eedo.project.zipte.domain.order.DeliveryStatus;

@Data
@Builder
public class DeliveryResponse {

    private String detailAddress;
    private String streetAddress;
    private int zipCode;
    private String orderDesc;
    private String deliveryDesc;
    private DeliveryStatus deliveryStatus;

    public static DeliveryResponse from(Delivery delivery) {
        return DeliveryResponse.builder()
                .detailAddress(delivery.getAddress().getDetailAddress())
                .streetAddress(delivery.getAddress().getStreetAddress())
                .zipCode(delivery.getAddress().getZipcode())
                .orderDesc(delivery.getOrderDesc())
                .deliveryDesc(delivery.getDeliveryDesc())
                .deliveryStatus(delivery.getStatus())
                .build();
    }
}
