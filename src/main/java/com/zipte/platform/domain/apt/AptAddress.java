package com.zipte.platform.domain.apt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AptAddress {

    private String region;
    private String streetAddress;
    private String detailAddress;
    private int zipcode;

    // 정적 팩토리 메서드
    public static AptAddress of(String region, String streetAddress, String detailAddress, int zipcode) {
        return AptAddress.builder()
                .region(region)
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .zipcode(zipcode)
                .build();
    }
}
