package org.gdg.zipte_gdg.api.service.toss;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.Toss.request.ConfirmPaymentRequestDto;
import org.gdg.zipte_gdg.api.service.toss.response.TossPaymentResponseDto;
import org.gdg.zipte_gdg.domain.Toss.TossPayment;
import org.gdg.zipte_gdg.domain.Toss.TossPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;

/*
    토스에서 넘어온 결제 내용을 저장하는 서비스 구현체
 */

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private final TossPaymentRepository tossPaymentRepository;

    @Override
    public TossPaymentResponseDto savePayment(ConfirmPaymentRequestDto confirmPaymentRequest, HttpResponse<String> response) throws Exception {

        TossPayment payment = TossPayment.createPayment(confirmPaymentRequest.getPaymentKey(),
                confirmPaymentRequest.getOrderId(),
                confirmPaymentRequest.getAmount());

        TossPayment save = tossPaymentRepository.save(payment);
        log.info("Payment saved: " + save);
        return  entityToDto(save);
    }
}
