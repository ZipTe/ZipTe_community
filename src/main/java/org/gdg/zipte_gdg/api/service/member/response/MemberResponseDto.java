package org.gdg.zipte_gdg.api.service.member.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberResponseDto{

    private Long id;
    private String email;
    private String username;
    private String phoneNumber;
    private String city;
    private String streetAddress;
    private List<String> roles;
    private int zipCode;
}
