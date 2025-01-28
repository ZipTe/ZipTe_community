package com.eedo.project.zipte.representation.in.web.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.PaymentService;
import com.eedo.project.zipte.representation.response.PaymentResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
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
