package org.gdg.zipte_gdg.api.service.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String city;
    private String streetAddress;
    private int zipcode;

    // 사용자 입력 사항
    private String orderDesc;
    private String deliveryDesc;

    // 아이템
    private List<OrderItemResponseDto> items; // 여러 상품 정보를 담는 리스트

}
