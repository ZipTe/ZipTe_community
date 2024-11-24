package org.gdg.zipte_gdg.security.oauth.domain;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.security.oauth.service.response.CustomUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class PrincipalDetails implements OAuth2User, UserDetails, Serializable {
    private final CustomUserDto customUserDto;

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return customUserDto.getRoles().toString();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getName() {

        return "";
    }

    public String getUsername() {

        return customUserDto.getUsername();
    }

    public String getEmail() {
        return customUserDto.getEmail();
    }

    public Long getId() {
        return customUserDto.getId();
    }
}
