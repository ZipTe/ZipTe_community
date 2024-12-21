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
    private Long id;

    // 멤버
    private Long memberId;
    private String memberName;

    // 주소
    private String detailAddress;
    private String streetAddress;
    private int zipcode;

    // 사용자 입력 사항
    private String orderDesc;
    private String deliveryDesc;

    // 아이템
    private List<OrderItemResponseDto> items; // 여러 상품 정보를 담는 리스트

    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;
}
