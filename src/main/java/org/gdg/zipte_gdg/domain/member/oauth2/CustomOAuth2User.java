package org.gdg.zipte_gdg.domain.member.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.gdg.zipte_gdg.api.service.member.response.oauth2.OAuth2UserResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2UserResponse oAuth2UserResponse;
    private final String role;

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
                return role;
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return oAuth2UserResponse.getName();
    }

    public String getUsername() {
        return oAuth2UserResponse.getProvider() + " " + oAuth2UserResponse.getProviderId();
    }
}
