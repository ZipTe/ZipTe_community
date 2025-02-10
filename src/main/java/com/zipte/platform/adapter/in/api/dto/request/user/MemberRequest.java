package com.zipte.platform.adapter.in.api.dto.request.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRequest {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String streetAddress;
    private String detailAddress;
    private int zipCode;
}
