package org.gdg.zipte_gdg.api.service.order;


import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderItemResponseDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderResponseDto;
import org.gdg.zipte_gdg.api.service.order.response.PaymentOrderResponseDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.domain.order.Order;
import org.gdg.zipte_gdg.domain.orderItem.OrderItem;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    // 주문 생성
    PaymentOrderResponseDto order(OrderRequestDto orderRequestDto);

    // 주문 번호로 찾기
    OrderResponseDto getOne(Long id);

    // 나의 주문 목록 조회 <User>
    PageResponseDto<OrderResponseDto> findMyOrders(PageRequestDto pageRequestDto, Long memberId);

    // DTO로 출력
    default OrderResponseDto entityToDTO(Order order) {
        OrderResponseDto responseDto = OrderResponseDto.builder()
                .id(order.getId())
                .memberId(order.getMember().getId())
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

    default PaymentOrderResponseDto entityToPaymentDTO(Order order) {
        // 첫 번째 상품 외 X건 형식으로 orderName 작성
        String orderName = generateOrderName(order);

        // 총 금액 계산
        int amount = 0;
        for (OrderItem item : order.getOrderItems()) {
            item.totalPrice();  // 각 item에 대해 totalPrice 계산
            amount += item.getPrice(); // 총 금액 누적
        }

        // 고객 전화번호 포맷 변경
        String phoneNumber = formatPhoneNumber(order.getMember().getPhoneNumber());


        // PaymentOrderResponseDto 생성
        PaymentOrderResponseDto responseDto = PaymentOrderResponseDto.builder()
                .orderId(String.valueOf(order.getId())) // 주문 ID를 String으로 변환
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
        String firstProductName = orderItems.get(0).getProduct().getPname();

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
        // 전화번호에서 "-"을 제거하고 숫자만 남기기
        return phoneNumber.replaceAll("[^0-9]", "");
    }



}

