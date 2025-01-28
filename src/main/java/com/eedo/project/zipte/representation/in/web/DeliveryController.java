package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.order.DeliveryRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.DeliveryService;
import com.eedo.project.zipte.representation.response.DeliveryResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송 관련 정보 수정
    @PutMapping("/{tossOrderId}")
    public ApiResponse<DeliveryResponse> updateState(@PathVariable("tossOrderId")String tossOrderId, @RequestBody DeliveryRequest deliveryRequest) {
        deliveryRequest.setTossOrderId(tossOrderId);
        return ApiResponse.created(deliveryService.updateAddress(deliveryRequest));
    }
}
