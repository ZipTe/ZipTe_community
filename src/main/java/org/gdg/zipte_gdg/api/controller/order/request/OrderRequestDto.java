package org.gdg.zipte_gdg.api.controller.order.request;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDto {

    // 오더
    private Long id;

    // 멤버
    private Long memberId;
    private String memberName;

    // 주소
    private String city;
    private String streetAddress;
    private int zipcode;

    // 사용자 입력 사항
    private String orderDesc;
    private String deliveryDesc;

    // 아이템
    private List<OrderItemRequestDto> items; // 여러 상품 정보를 담는 리스트

}
