package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;
import com.eedo.project.zipte.domain.order.Delivery;
import com.eedo.project.zipte.domain.order.Order;
import com.eedo.project.zipte.domain.order.OrderStatus;
import com.eedo.project.zipte.domain.order.OrderItem;

import java.util.List;

@Data
@Builder
public class OrderResponse {

    // 오더
    private String tossOrderId;

    // 멤버
    private String memberName;

    // 주문 상황
    private DeliveryResponse delivery;

    // 아이템
    private List<OrderItemResponse> items; // 여러 상품 정보를 담는 리스트

    // 오더 주문 상황
    private OrderStatus orderStatus;

    // 생성자
    public static OrderResponse from(Order order) {

        Delivery delivery = order.getDelivery();
        DeliveryResponse deliveryResponse = DeliveryResponse.from(delivery);

        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemResponse> items = OrderItemResponse.froms(orderItems);

        return OrderResponse.builder()
                .tossOrderId(order.getTossOrderId())
                .memberName(order.getMember().getUsername())
                .items(items)
                .delivery(deliveryResponse)
                .orderStatus(order.getStatus())
                .build();
    }

}
