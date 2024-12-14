package org.gdg.zipte_gdg.security.oauth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@Getter
public class PrincipalDetails implements OAuth2User, UserDetails, Serializable {

    private final Member member;
//    private final Map<String, Object> attributes;
//    private final String attributeKey;

    // 첫 번째 생성자: Member만 받는 생성자
    public PrincipalDetails(Member member) {
        this.member = member;
//        this.attributes = Map.of(); // 기본값 빈 맵으로 설정
//        this.attributeKey = "";
    }

//    // 두 번째 생성자: Member와 속성(attributeKey)를 받는 생성자
//    public PrincipalDetails(Member member, Map<String, Object> attributes, String attributeKey) {
//        this.member = member;
//        this.attributes = attributes;
//        this.attributeKey = attributeKey;
//    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getRoles().stream().map(o -> new SimpleGrantedAuthority(
                o.getRole()
        )).collect(Collectors.toList());
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
}
