package org.gdg.zipte.api.service.order.delivery.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte.domain.order.delivery.Delivery;
import org.gdg.zipte.domain.order.delivery.DeliveryStatus;

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
