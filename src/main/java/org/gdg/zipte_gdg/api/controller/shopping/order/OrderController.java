package org.gdg.zipte_gdg.api.controller.shopping.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte_gdg.api.controller.shopping.order.request.OrderRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.order.OrderService;
import org.gdg.zipte_gdg.api.service.shopping.order.response.TossOrderResponse;
import org.gdg.zipte_gdg.api.service.shopping.payment.PaymentService;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.OrderResponse;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.PaymentResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    // 가주문 오더 생성
    @PostMapping
    public ApiResponse<TossOrderResponse> create(@RequestBody OrderRequest orderRequest) {
        return ApiResponse.created(orderService.order(orderRequest));
    }

    // 나의 결제 내역 조회
    @GetMapping("/myOrder")
    public  ApiResponse<PageResponseDto<PaymentResponse>> getMyAllPayment(@AuthenticationPrincipal PrincipalDetails principalDetails, PageRequestDto pageRequestDto) {
        Long memberId = principalDetails.getId();
        return ApiResponse.created(paymentService.findMyPayments(memberId,pageRequestDto));
    }

    // 토스 결제 내역으로 주문 내역 확인
    @GetMapping("/{tossOrderId}")
    public ApiResponse<OrderResponse> payment(@PathVariable("tossOrderId") String tossOrderId) throws Exception {
        return ApiResponse.created(paymentService.getDetails(tossOrderId));
    }



}
