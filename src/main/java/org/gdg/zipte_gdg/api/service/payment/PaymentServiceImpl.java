package org.gdg.zipte_gdg.api.service.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.gdg.zipte_gdg.api.controller.toss.request.ConfirmPaymentRequestDto;
import org.gdg.zipte_gdg.api.service.toss.response.TossPaymentResponseDto;
import org.gdg.zipte_gdg.domain.order.Order;
import org.gdg.zipte_gdg.domain.order.OrderRepository;
import org.gdg.zipte_gdg.domain.toss.TossPayment;
import org.gdg.zipte_gdg.domain.toss.TossPaymentRepository;
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
    private final OrderRepository orderRepository;

    @Override
    public TossPaymentResponseDto savePayment(ConfirmPaymentRequestDto confirmPaymentRequest, HttpResponse<String> response) throws Exception {

        JSONParser jsonParser = new JSONParser();
        JSONObject responseBody = (JSONObject) jsonParser.parse(response.body());

        String orderId = confirmPaymentRequest.getOrderId();
        Order order = orderRepository.findByTossOrderId(orderId).orElseThrow();

        // TossPayment 객체 생성
        TossPayment tossPayment = TossPayment.builder()
                .tossPaymentKey((String) responseBody.get("paymentKey"))
                .tossOrderId(order.getTossOrderId())
                .order(order)
                .tossPaymentMethod((String) responseBody.get("method"))
                .tossOrderName((String) responseBody.get("orderName"))
                .tossPaymentStatus((String) responseBody.get("status"))
                .totalAmount(((int) responseBody.get("totalAmount")))
                .requestedAt((String) responseBody.get("requestedAt"))
                .build();

        // 결제가 완료 되어야 재고를 없앤다
        order.getOrderItems().forEach(orderItem -> {
            orderItem.getProduct().removeStock(orderItem.getCount());
        });

        // 결제 정보 저장
        TossPayment savedPayment = tossPaymentRepository.save(tossPayment);
        log.info("Payment saved: " + savedPayment);

        // DTO로 변환하여 반환
        return entityToDto(savedPayment);
    }
}
