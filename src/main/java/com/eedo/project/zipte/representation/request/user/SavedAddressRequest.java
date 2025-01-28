package com.eedo.project.zipte.representation.request.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SavedAddressRequest {

    // 유저 ID
    private Long memberId;

    // 주소
    private String streetAddress;
    private String detailAddress;
    private int zipcode;

    // 사용자 입력 사항
    private String orderDesc;
    private String deliveryDesc;

    // 기본 배송지
    private Boolean isDefault;

}
