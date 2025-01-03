package org.gdg.zipte_gdg.api.service.shopping.delivery.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.domain.shopping.delivery.Delivery;
import org.gdg.zipte_gdg.domain.shopping.delivery.DeliveryStatus;

@Data
@Builder
public class DeliveryResponse {

    private Long id;
    private String detailAddress;
    private String streetAddress;
    private int zipCode;
    private String orderDesc;
    private String deliveryDesc;
    private DeliveryStatus deliveryStatus;

    public static DeliveryResponse from(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .detailAddress(delivery.getAddress().getDetailAddress())
                .streetAddress(delivery.getAddress().getStreetAddress())
                .zipCode(delivery.getAddress().getZipcode())
                .orderDesc(delivery.getOrderDesc())
                .deliveryDesc(delivery.getDeliveryDesc())
                .deliveryStatus(delivery.getStatus())
                .build();
    }
}
