package com.eedo.project.zipte.application.service.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import com.eedo.project.zipte.application.port.in.PaymentService;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.zipte.representation.request.toss.ConfirmPaymentRequest;
import com.eedo.project.zipte.representation.response.OrderResponse;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.zipte.representation.response.PaymentResponse;
import com.eedo.project.zipte.infrastructure.out.pg.toss.response.TossPaymentResponse;
import com.eedo.project.zipte.domain.order.Order;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.order.OrderRepository;
import com.eedo.project.zipte.domain.payment.Payment;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.payment.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
import java.util.List;

/*
    토스에서 넘어온 결제 내용을 저장하는 서비스 구현체
 */

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public TossPaymentResponse savePayment(ConfirmPaymentRequest confirmPaymentRequest, HttpResponse<String> response) throws Exception {

        JSONParser jsonParser = new JSONParser();
        JSONObject responseBody = (JSONObject) jsonParser.parse(response.body());

        String orderId = confirmPaymentRequest.getOrderId();
        Order order = orderRepository.findByTossOrderId(orderId).orElseThrow();


        // TossPayment 객체 생성
        Payment payment = Payment.builder()
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
            orderItem.getProductManager().getProduct().removeStock(orderItem.getCount());
        });

        // 결제 정보 저장
        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment saved: " + savedPayment);

        // DTO로 변환하여 리액트에 반환
        return TossPaymentResponse.from(savedPayment);
    }

    @Override
    public OrderResponse getDetails(String orderId) throws Exception {
        Order order = orderRepository.findByTossOrderId(orderId).orElseThrow();

        return OrderResponse.from(order);
    }

    @Override
    public PageResponse<PaymentResponse> findMyPayments(Long memberId, PageRequest pageRequest) {

        // 페이지 정보와 정렬 기준 설정
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(),Sort.by("paymentId").descending());

        // 회원 ID로 결제 정보 조회
        Page<Payment> payments = paymentRepository.getMyAllPayment(memberId,pageable);

        // 결제 정보가 존재하는 경우 DTO로 변환
        List<PaymentResponse> dtoList = PaymentResponse.froms(payments.getContent());

        // 전체 결제 건수
        long total = payments.getTotalElements();

        // 페이지 응답 반환
        return new PageResponse<>(dtoList, pageRequest, total);
    }

}
