package org.gdg.zipte_gdg.api.controller.shopping.order.request;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {

    // 오더
    private Long id;

    // 멤버
    private Long memberId;

    // 주소
    private String streetAddress;
    private String detailAddress;
    private int zipcode;

    // 사용자 입력 사항
    private String orderDesc;
    private String deliveryDesc;

    // 아이템
    private List<OrderItemRequest> items; // 여러 상품 정보를 담는 리스트

}
