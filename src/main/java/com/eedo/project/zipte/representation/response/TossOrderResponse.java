package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import com.eedo.project.zipte.domain.order.Order;
import com.eedo.project.zipte.domain.order.OrderItem;

import java.util.List;

@Data
@Getter
@Builder
public class TossOrderResponse {

    // 오더
    private String orderId;

    // 아이템
    private String orderName;
    private String amount;

    // 멤버
    private String customerName;
    private String customerEmail;
    private String customerMobilePhone;


    // 생성자
    public static TossOrderResponse of(Order order) {
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
        TossOrderResponse responseDto = TossOrderResponse.builder()
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
    private static String generateOrderName(Order order) {
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
    private static String formatPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceAll("[^0-9]", "");
    }


}
