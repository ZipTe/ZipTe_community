package org.gdg.zipte_gdg.api.controller.shopping.delivery;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.delivery.request.DeliveryRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.delivery.DeliveryService;
import org.gdg.zipte_gdg.api.service.shopping.delivery.response.DeliveryResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/{deliveryId}")
    public ApiResponse<DeliveryResponse> getOne (@PathVariable("deliveryId") Long deliveryId) {
        return ApiResponse.created(deliveryService.findById(deliveryId));
    }

    @PutMapping("/{deliveryId}")
    public ApiResponse<DeliveryResponse> updateState(@PathVariable("deliveryId")Long deliveryId, @RequestBody DeliveryRequest deliveryRequest) {
        deliveryRequest.setId(deliveryId);
        return ApiResponse.created(deliveryService.updateOne(deliveryRequest));
    }
}
