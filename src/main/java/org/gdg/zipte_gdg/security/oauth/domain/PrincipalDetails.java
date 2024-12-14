package org.gdg.zipte_gdg.security.oauth.domain;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record PrincipalDetails(
        Member member,
        Map<String, Object> attributes,
        String attributeKey) implements OAuth2User, UserDetails, Serializable {

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Member의 roles를 기반으로 GrantedAuthority 리스트 생성
        return member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getName() {
        // OAuth2User의 고유 식별자 (예: 이메일)
        return member.getEmail();
    }

    public String getUsername() {
        return member.getUsername();
    }

    public Long getId() {
        return member.getId();
    }

    public List<String> getRoles() {
        // 사용자 역할 목록
        return member.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
