package org.gdg.zipte_gdg.api.controller.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.order.OrderService;
import org.gdg.zipte_gdg.api.service.order.response.PaymentOrderResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ApiResponse<PaymentOrderResponseDto> create(@RequestBody OrderRequestDto orderRequestDto) {
        return ApiResponse.created(orderService.order(orderRequestDto));
    }

}
