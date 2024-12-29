package org.gdg.zipte_gdg.api.service.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.toss.request.ConfirmPaymentRequestDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderResponseDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.payment.response.PaymentResponseDto;
import org.gdg.zipte_gdg.api.service.toss.response.TossPaymentResponseDto;
import org.gdg.zipte_gdg.domain.eCommerce.order.Order;
import org.gdg.zipte_gdg.domain.eCommerce.order.OrderRepository;
import org.gdg.zipte_gdg.domain.eCommerce.payment.Payment;
import org.gdg.zipte_gdg.domain.eCommerce.payment.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

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
    public TossPaymentResponseDto savePayment(ConfirmPaymentRequestDto confirmPaymentRequest, HttpResponse<String> response) throws Exception {

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
            orderItem.getProduct().removeStock(orderItem.getCount());
        });

        // 결제 정보 저장
        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment saved: " + savedPayment);

        // DTO로 변환하여 반환
        return entityToDto(savedPayment);
    }

    @Override
    public OrderResponseDto getDetails(String orderId) throws Exception {
        Order order = orderRepository.findByTossOrderId(orderId).orElseThrow();

        return entityToDTO(order);
    }

    @Override
    public PageResponseDto<PaymentResponseDto> findMyPayments(Long memberId, PageRequestDto pageRequestDto) {

        // 페이지 정보와 정렬 기준 설정
        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize(),Sort.by("paymentId").descending());

        // 회원 ID로 결제 정보 조회
        Page<Payment> payments = paymentRepository.getMyAllPayment(memberId,pageable);
        log.info("MyLOG: Retrieved payments for memberId {}: {}", memberId, payments.getContent());

        // 결제 정보가 존재하는 경우 DTO로 변환
        List<PaymentResponseDto> dtoList = payments.stream()
                .map(this::entityToPaymentDto)
                .collect(Collectors.toList());

        // 전체 결제 건수
        long total = payments.getTotalElements();

        // 페이지 응답 반환
        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }

}
