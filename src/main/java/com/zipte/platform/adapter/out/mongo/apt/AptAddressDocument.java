package com.zipte.platform.adapter.out.mongo.apt;

import com.zipte.platform.domain.apt.AptAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AptAddressDocument {

    private String region;
    private String streetAddress;
    private String detailAddress;
    private int zipcode;

    public static AptAddressDocument from(AptAddress aptAddress) {
        return AptAddressDocument.builder()
                .region(aptAddress.getRegion())
                .streetAddress(aptAddress.getStreetAddress())
                .detailAddress(aptAddress.getDetailAddress())
                .zipcode(aptAddress.getZipcode())
                .build();
    }

    public AptAddress toDomain() {
        return AptAddress.builder()
                .region(this.getRegion())
                .streetAddress(this.getStreetAddress())
                .detailAddress(this.getDetailAddress())
                .zipcode(this.getZipcode())
                .build();
    }
}
