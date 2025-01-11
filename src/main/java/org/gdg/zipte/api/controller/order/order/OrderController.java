package org.gdg.zipte.api.controller.order.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte.api.controller.order.order.request.OrderRequest;
import org.gdg.zipte.api.common.ApiResponse;
import org.gdg.zipte.api.service.order.order.OrderService;
import org.gdg.zipte.api.service.order.order.response.TossOrderResponse;
import org.gdg.zipte.api.service.order.payment.PaymentService;
import org.gdg.zipte.api.service.order.payment.response.OrderResponse;
import org.gdg.zipte.api.service.order.payment.response.PaymentResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
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
    public ApiResponse<TossOrderResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody OrderRequest orderRequest) {
        orderRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(orderService.order(orderRequest));
    }

    // 나의 결제 내역 조회
    @GetMapping("/myOrder")
    public  ApiResponse<PageResponse<PaymentResponse>> getMyAllPayment(@AuthenticationPrincipal PrincipalDetails principalDetails, PageRequest pageRequest) {
        Long memberId = principalDetails.getId();
        return ApiResponse.created(paymentService.findMyPayments(memberId, pageRequest));
    }

    // 토스 결제 내역으로 주문 내역 확인
    @GetMapping("/{tossOrderId}")
    public ApiResponse<OrderResponse> payment(@PathVariable("tossOrderId") String tossOrderId) throws Exception {
        return ApiResponse.created(paymentService.getDetails(tossOrderId));
    }



}
