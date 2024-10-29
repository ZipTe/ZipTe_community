package org.gdg.zipte_gdg.api.controller.member.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRequestDto {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String city;
    private String streetAddress;
    private int zipCode;
}
