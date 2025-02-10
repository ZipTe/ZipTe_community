package com.zipte.core.security.oauth.service;

import com.zipte.platform.adapter.out.jpa.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.zipte.core.security.oauth.service.response.NaverResponse;
import com.zipte.core.security.oauth.service.response.OAuth2UserResponse;
import com.zipte.platform.domain.user.Address;
import com.zipte.platform.domain.user.Member;
import com.zipte.core.security.oauth.domain.PrincipalDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        // 소셜 사용자 정보 가져오기
        Map<String, Object> oAuth2UserAttributes = super.loadUser(request).getAttributes();

        // userNameAttributeName 가져오기
        String userNameAttributeName = request.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2 클라이언트 정보
        String registrationId = request.getClientRegistration().getRegistrationId();

        OAuth2UserResponse oAuth2UserResponse = null;
        String email = null;
        String username = null;
        String phoneNumber = null;

        // 네이버 사용자 정보 처리
        if (registrationId.equals("naver")) {
            oAuth2UserResponse = new NaverResponse(oAuth2UserAttributes);
            email = oAuth2UserResponse.getEmail();
            username = oAuth2UserResponse.getProvider() + " " + oAuth2UserResponse.getName();
            phoneNumber = oAuth2UserResponse.getPhoneNumber();
        }

        // 기존 사용자 검색
        Member existingMember = memberRepository.findByEmail(email);

        if (existingMember == null) {
            // 신규 사용자 생성
            Address address = Address.of("미정", "미정", 11111);

            Member newMember = Member.of(email, username,  phoneNumber, address);
//            newMember.addMemberRole(Role.OAUTH_FIRST_JOIN); // 초기 권한 설정
            Member savedMember = memberRepository.save(newMember);

            log.info("신규 사용자 등록: {}", savedMember.getId());
            return new PrincipalDetails(savedMember);
        } else {
            // 기존 사용자 정보 업데이트
            existingMember.changeEmail(oAuth2UserResponse.getEmail());
            Member updatedMember = memberRepository.save(existingMember);

            log.info("기존 사용자 업데이트: {}", updatedMember);
            return new PrincipalDetails(updatedMember);
        }
    }


}
