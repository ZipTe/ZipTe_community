package org.gdg.zipte_gdg.api.controller.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.order.response.OrderResponseDto;
import org.gdg.zipte_gdg.api.service.order.OrderService;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponseDto> create(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody OrderRequestDto orderRequestDto) {
        orderRequestDto.setMemberId(principalDetails.getId());
        return ApiResponse.created(orderService.order(orderRequestDto));
    }

    @GetMapping({"/{id}"})
    public ApiResponse<OrderResponseDto> getOne(@PathVariable("id") Long id) {
        return ApiResponse.created(orderService.getOne(id));
    }

    @GetMapping({"/myOrder"})
    public ApiResponse<PageResponseDto<OrderResponseDto>> findMyOrder(@AuthenticationPrincipal PrincipalDetails principalDetails, PageRequestDto pageRequestDto) {
        Long memberId = principalDetails.getId();
        return ApiResponse.created(orderService.findMyOrders(pageRequestDto,memberId));
    }
}
