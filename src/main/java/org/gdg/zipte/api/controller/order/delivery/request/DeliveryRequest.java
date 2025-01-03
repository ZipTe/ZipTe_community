package org.gdg.zipte.api.controller.order.delivery.request;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
public class DeliveryRequest {

    @NotNull
    private String tossOrderId;  // Delivery ID

    private String streetAddress;
    private String detailAddress;
    private int zipCode;  // Zip code

    private String orderDesc;
    private String deliveryDesc;

}
