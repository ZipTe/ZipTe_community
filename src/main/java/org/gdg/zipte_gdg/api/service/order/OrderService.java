package org.gdg.zipte_gdg.api.service.order;


import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderItemResponseDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderResponseDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.domain.order.Order;

import java.util.ArrayList;

public interface OrderService {

    // 주문 생성
    OrderResponseDto register(OrderRequestDto orderRequestDto);

    // 주문 번호로 찾기
    OrderResponseDto getOne(Long id);

    // 나의 주문 목록 조회 <User>
    PageResponseDto<OrderResponseDto> findMyOrders(PageRequestDto pageRequestDto, Long memberId);

    default OrderResponseDto entityToDTO(Order order) {
        OrderResponseDto responseDto = OrderResponseDto.builder()
                .id(order.getId())
                .memberId(order.getMember().getId())
                .memberName(order.getMember().getUsername())
                .city(order.getDelivery().getAddress().getCity())
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

