package org.gdg.zipte_gdg.api.controller.delivery;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.delivery.request.DeliveryUpdateDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.delivery.DeliveryService;
import org.gdg.zipte_gdg.api.service.delivery.response.DeliveryResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/{deliveryId}")
    public ApiResponse<DeliveryResponseDto> getOne (@PathVariable("deliveryId") Long deliveryId) {
        return ApiResponse.created(deliveryService.findById(deliveryId));
    }

    @PutMapping("/{deliveryId}")
    public ApiResponse<DeliveryResponseDto> updateState(@PathVariable("deliveryId")Long deliveryId, @RequestBody DeliveryUpdateDto deliveryUpdateDto) {
        deliveryUpdateDto.setId(deliveryId);
        return ApiResponse.created(deliveryService.updateOne(deliveryUpdateDto));
    }
}
