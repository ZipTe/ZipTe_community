package org.gdg.zipte_gdg.api.service.member.response.oauth2;

public interface OAuth2UserResponse {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

    String getPhoneNumber();

}
