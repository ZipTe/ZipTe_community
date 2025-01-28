package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;
import com.eedo.project.zipte.domain.order.OrderItem;

import java.util.*;

@Data
@Builder
public class OrderItemResponse {

    private String productName;  // 상품 이름
    private int count;  // 상품 수량
    private int price; // 상품가격


    // 생성자
    public static OrderItemResponse from(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .productName(orderItem.getProductManager().getProduct().getPname())
                .count(orderItem.getCount())
                .price(orderItem.getPrice())
                .build();
    }

    public static List<OrderItemResponse> froms(List<OrderItem> orderItems) {
        List<OrderItemResponse> responseList = new ArrayList<>();

        orderItems.forEach(orderItem -> responseList.add(OrderItemResponse.from(orderItem)));
        return responseList;
    }
}

