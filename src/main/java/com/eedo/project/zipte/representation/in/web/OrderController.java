package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.eedo.project.zipte.representation.request.order.OrderRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.order.CreateOrderUseCase;
import com.eedo.project.zipte.representation.response.TossOrderResponse;
import com.eedo.project.zipte.application.port.in.PaymentService;
import com.eedo.project.zipte.representation.response.OrderResponse;
import com.eedo.project.zipte.representation.response.PaymentResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final PaymentService paymentService;

    // 가주문 오더 생성
    @PostMapping
    public ApiResponse<TossOrderResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody OrderRequest orderRequest) {
        orderRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(TossOrderResponse.of(createOrderUseCase.order(orderRequest)));
    }

    // 나의 결제 내역 조회
    @GetMapping("/myOrder")
    public  ApiResponse<PageResponse<PaymentResponse>> getMyAllPayment(@AuthenticationPrincipal PrincipalDetails principalDetails, PageRequest pageRequest) {
        Long memberId = principalDetails.getId();
        return ApiResponse.ok(paymentService.findMyPayments(memberId, pageRequest));
    }

    // 토스 결제 내역으로 주문 내역 확인
    @GetMapping("/{tossOrderId}")
    public ApiResponse<OrderResponse> payment(@PathVariable("tossOrderId") String tossOrderId) throws Exception {
        return ApiResponse.ok(paymentService.getDetails(tossOrderId));
    }



}
