package org.gdg.zipte_gdg.api.controller.shopping.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.OrderResponse;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.payment.PaymentService;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.PaymentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 유저별 결제 내역 조회
    @GetMapping("/member/{memberId}")
    public  ApiResponse<PageResponseDto<PaymentResponse>> getMyAllPayment(@PathVariable("memberId") Long memberId, PageRequestDto pageRequestDto) {
        return ApiResponse.created(paymentService.findMyPayments(memberId,pageRequestDto));
    }


    @GetMapping("/{tossOrderId}")
    public ApiResponse<OrderResponse> payment(@PathVariable("tossOrderId") String tossOrderId) throws Exception {
        return ApiResponse.created(paymentService.getDetails(tossOrderId));
    }
}
