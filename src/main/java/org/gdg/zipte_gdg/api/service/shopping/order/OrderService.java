package org.gdg.zipte_gdg.api.service.shopping.order;


import org.gdg.zipte_gdg.api.controller.shopping.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.service.shopping.order.response.PaymentOrderResponseDto;
import org.gdg.zipte_gdg.domain.shopping.order.Order;
import org.gdg.zipte_gdg.domain.shopping.orderItem.OrderItem;

import java.util.List;

public interface OrderService {

    // 토스를 위한 주문 Response 생성
    PaymentOrderResponseDto order(OrderRequestDto orderRequestDto);

    default PaymentOrderResponseDto entityToPaymentDTO(Order order) {
        // 첫 번째 상품 외 X건 형식으로 orderName 작성
        String orderName = generateOrderName(order);

        // 총 금액 계산
        int amount = 0;
        for (OrderItem item : order.getOrderItems()) {
            item.getTotalPrice();  // 각 item에 대해 totalPrice 계산
            amount += item.getPrice(); // 총 금액 누적
        }

        // 고객 전화번호 포맷 변경
        String phoneNumber = formatPhoneNumber(order.getMember().getPhoneNumber());


        // PaymentOrderResponseDto 생성
        PaymentOrderResponseDto responseDto = PaymentOrderResponseDto.builder()
                .orderId(order.getTossOrderId()) // 주문 ID를 String으로 변환
                .orderName(orderName) // 첫번째 상품 외 X건
                .amount(String.valueOf(amount)) // 총 금액을 String으로 변환하여 설정
                .customerName(order.getMember().getUsername()) // 고객 이름
                .customerEmail(order.getMember().getEmail()) // 고객 이메일
                .customerMobilePhone(phoneNumber) // 고객 전화번호
                .build();

        return responseDto;
    }

    // orderName을 "첫번째 상품 외 X건" 형식으로 생성하는 메소드
    private String generateOrderName(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems.isEmpty()) {
            return "No Items"; // 혹시 빈 주문일 경우
        }

        // 첫 번째 상품명
        String firstProductName = orderItems.get(0).getProductManager().getProduct().getPname();

        // 나머지 상품 수
        int remainingCount = orderItems.size() - 1;

        // "첫번째 상품 외 X건" 형태로 작성
        if (remainingCount > 0) {
            return String.format("%s 외 %d건", firstProductName, remainingCount);
        } else {
            return firstProductName; // 나머지가 없으면 첫 번째 상품명만
        }
    }

    // 전화번호 포맷을 "01000000000" 형식으로 맞추는 메소드
    private String formatPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceAll("[^0-9]", "");
    }


}

