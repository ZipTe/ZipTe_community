package org.gdg.zipte_gdg.api.service.shopping.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.shopping.toss.request.ConfirmPaymentRequest;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.OrderResponse;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.payment.response.PaymentResponse;
import org.gdg.zipte_gdg.api.service.shopping.toss.response.TossPaymentResponse;
import org.gdg.zipte_gdg.domain.order.order.Order;
import org.gdg.zipte_gdg.domain.order.order.OrderRepository;
import org.gdg.zipte_gdg.domain.order.payment.Payment;
import org.gdg.zipte_gdg.domain.order.payment.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public PageResponseDto<PaymentResponse> findMyPayments(Long memberId, PageRequestDto pageRequestDto) {

        // 페이지 정보와 정렬 기준 설정
        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize(),Sort.by("paymentId").descending());

        // 회원 ID로 결제 정보 조회
        Page<Payment> payments = paymentRepository.getMyAllPayment(memberId,pageable);
        log.info("MyLOG: Retrieved payments for memberId {}: {}", memberId, payments.getContent());

        // 결제 정보가 존재하는 경우 DTO로 변환
        List<PaymentResponse> dtoList = PaymentResponse.froms(payments.getContent());

        // 전체 결제 건수
        long total = payments.getTotalElements();

        // 페이지 응답 반환
        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }

}
