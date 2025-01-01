package org.gdg.zipte_gdg.domain.user.member;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Address implements Serializable {


    private String streetAddress;

    private String detailAddress;

    private int zipcode;

    public static Address newAddress(String detailAddress, String streetAddress, int zipcode) {
        return Address.builder()
                .detailAddress(detailAddress)
                .streetAddress(streetAddress)
                .zipcode(zipcode).build();
    }

}
