package org.gdg.zipte_gdg.api.service.member.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class MemberResponseDto implements UserDetails {

    private Long id;
    private String email;
    private String username;
    private String phoneNumber;
    private String city;
    private String streetAddress;
    private int zipCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }
}
