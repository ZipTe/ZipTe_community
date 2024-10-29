package org.gdg.zipte_gdg.api.controller.delivery.request;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.gdg.zipte_gdg.domain.delivery.DeliveryStatus;

@Data
@Builder
public class DeliveryUpdateDto {

    @NotNull
    private Long id;  // Delivery ID

    private DeliveryStatus status;  // Delivery status

    private DeliveryAddressDto address;  // Delivery address, if needed

    // If needed, you can define a description field
    private DeliveryDescDto description;

    @Data
    @Builder
    public static class DeliveryStatusDto {
        private Long id;
        private String status;
    }

    @Data
    @Builder
    public static class DeliveryAddressDto {
        private Long id;
        private String city;
        private String streetAddress;
        private int zipCode;  // Zip code
    }

    @Data
    @Builder
    public static class DeliveryDescDto {
        private Long id;
        private String orderDesc;
        private String deliveryDesc;
    }
}
