package org.gdg.zipte_gdg.domain.member;

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

    private String city;

    private String streetAddress;

    private int zipcode;

    public static Address newAddress(String city, String streetAddress, int zipcode) {
        return Address.builder()
                .city(city)
                .streetAddress(streetAddress)
                .zipcode(zipcode).build();
    }

}
