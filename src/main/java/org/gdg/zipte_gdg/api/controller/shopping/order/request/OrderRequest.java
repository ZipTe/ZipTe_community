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
    private Long savedAddressId;

    // 아이템
    private List<OrderItemRequest> items; // 여러 상품 정보를 담는 리스트

}
