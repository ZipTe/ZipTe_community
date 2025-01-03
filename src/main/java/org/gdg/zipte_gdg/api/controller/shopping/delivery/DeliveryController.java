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

    // 배송 조회
    @GetMapping("/{deliveryId}")
    public ApiResponse<DeliveryResponse> getOne (@PathVariable("deliveryId") Long deliveryId) {
        return ApiResponse.created(deliveryService.findById(deliveryId));
    }

    // 배송 관련 정보 수정
    @PutMapping("/{orderId}")
    public ApiResponse<DeliveryResponse> updateState(@PathVariable("orderId")Long orderId, @RequestBody DeliveryRequest deliveryRequest) {
        deliveryRequest.setOrderId(orderId);
        return ApiResponse.created(deliveryService.updateAddress(deliveryRequest));
    }
}
