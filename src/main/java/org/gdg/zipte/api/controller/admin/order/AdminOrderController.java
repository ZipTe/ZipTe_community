package org.gdg.zipte.api.controller.admin.order;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.order.payment.PaymentService;
import org.gdg.zipte.api.service.order.payment.response.PaymentResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequestMapping("/admin/order")
@RequiredArgsConstructor
@RestController
public class AdminOrderController {

    private final PaymentService paymentService;

    // 유저별 결제 내역 조회
    @GetMapping("/member/{memberId}")
    public ApiResponse<PageResponse<PaymentResponse>> getMyAllPayment(@PathVariable("memberId") Long memberId, PageRequest pageRequest) {
        return ApiResponse.created(paymentService.findMyPayments(memberId, pageRequest));
    }


}
