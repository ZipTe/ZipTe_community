package org.gdg.zipte_gdg.api.service.payment;


import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.toss.request.ConfirmPaymentRequestDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderItemResponseDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderResponseDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.payment.response.PaymentResponseDto;
import org.gdg.zipte_gdg.api.service.toss.response.TossPaymentResponseDto;
import org.gdg.zipte_gdg.domain.eCommerce.order.Order;
import org.gdg.zipte_gdg.domain.eCommerce.payment.Payment;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public interface PaymentService {


    // 리액트에게 결과 리턴
    TossPaymentResponseDto savePayment(ConfirmPaymentRequestDto confirmPaymentRequest , HttpResponse<String> response) throws Exception;

    // 결제에서 상세 주문정보 얻어오기
    OrderResponseDto getDetails(String orderId) throws Exception;

    // 내 결제 목록 가져오기
    PageResponseDto<PaymentResponseDto> findMyPayments(Long memberId,PageRequestDto pageRequestDto);


    default TossPaymentResponseDto entityToDto(Payment payment) throws Exception {
        return TossPaymentResponseDto.builder()
                .orderId(payment.getTossOrderId())
                .amount(payment.getTotalAmount())
                .build();

    }

    default PaymentResponseDto entityToPaymentDto(Payment payment) {
        return PaymentResponseDto.builder()
                .tossOrderId(payment.getTossOrderId())
                .tossOrderName(payment.getTossOrderName())
                .totalAmount(payment.getTotalAmount())
                .requestedAt(payment.getRequestedAt())
                .build();


    }

    // DTO로 출력
    default OrderResponseDto entityToDTO(Order order) {
        OrderResponseDto responseDto = OrderResponseDto.builder()
                .tossOrderId(order.getTossOrderId())
                .memberName(order.getMember().getUsername())
                .detailAddress(order.getDelivery().getAddress().getDetailAddress())
                .streetAddress(order.getDelivery().getAddress().getStreetAddress())
                .zipcode(order.getDelivery().getAddress().getZipcode())
                .orderDesc(order.getDelivery().getOrderDesc())
                .deliveryDesc(order.getDelivery().getDeliveryDesc())
                .items(new ArrayList<>()) // 아이템 리스트 초기화
                .orderStatus(order.getStatus())
                .deliveryStatus(order.getDelivery().getStatus())
                .build();

        // 각 OrderItem에 대해 정보를 DTO로 변환하여 리스트에 추가
        order.getOrderItems().forEach(orderItem -> {
            OrderItemResponseDto itemDto = OrderItemResponseDto.builder()
                    .productId(orderItem.getProduct().getId())
                    .productName(orderItem.getProduct().getPname())
                    .count(orderItem.getCount())
                    .price(orderItem.getPrice())
                    .build();

            responseDto.getItems().add(itemDto); // 리스트에 추가
        });

        return responseDto;
    }
}
