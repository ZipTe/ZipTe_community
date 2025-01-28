package com.eedo.project.core.security.oauth.service.response;

public interface OAuth2UserResponse {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

    String getPhoneNumber();

}
