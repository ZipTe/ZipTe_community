package org.gdg.zipte.api.controller.order.delivery;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.order.delivery.request.DeliveryRequest;
import org.gdg.zipte.api.common.ApiResponse;
import org.gdg.zipte.api.service.order.delivery.DeliveryService;
import org.gdg.zipte.api.service.order.delivery.response.DeliveryResponse;
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
    @PutMapping("/{tossOrderId}")
    public ApiResponse<DeliveryResponse> updateState(@PathVariable("tossOrderId")String tossOrderId, @RequestBody DeliveryRequest deliveryRequest) {
        deliveryRequest.setTossOrderId(tossOrderId);
        return ApiResponse.created(deliveryService.updateAddress(deliveryRequest));
    }
}
