package org.gdg.zipte_gdg.api.controller.shopping.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte_gdg.api.controller.shopping.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.order.OrderService;
import org.gdg.zipte_gdg.api.service.shopping.order.response.PaymentOrderResponseDto;
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
