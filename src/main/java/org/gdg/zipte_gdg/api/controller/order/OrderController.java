package org.gdg.zipte_gdg.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.order.response.OrderResponseDto;
import org.gdg.zipte_gdg.api.service.order.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponseDto> create(@RequestBody OrderRequestDto orderRequestDto) {
        return ApiResponse.created(orderService.register(orderRequestDto));
    }

    @GetMapping({"/{id}"})
    public ApiResponse<OrderResponseDto> getOne(@PathVariable("id") Long id) {
        return ApiResponse.created(orderService.getOne(id));
    }
}
