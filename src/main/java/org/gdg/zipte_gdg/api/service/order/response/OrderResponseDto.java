package org.gdg.zipte_gdg.api.service.order.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.domain.delivery.DeliveryStatus;
import org.gdg.zipte_gdg.domain.order.OrderStatus;

import java.util.List;

@Data
@Builder
public class OrderResponseDto {

    // 오더
    private String tossOrderId;

    // 멤버
    private String memberName;

    // 주소
    private String streetAddress;
    private String detailAddress;
    private int zipcode;

    // 아이템
    private List<OrderItemResponseDto> items; // 여러 상품 정보를 담는 리스트

    // 오더 주문 상황
    private OrderStatus orderStatus;
    private String orderDesc;

    // 배송 상황
    private DeliveryStatus deliveryStatus;
    private String deliveryDesc;

}
