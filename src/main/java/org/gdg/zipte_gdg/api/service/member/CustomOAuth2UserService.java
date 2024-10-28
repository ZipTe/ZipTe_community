package org.gdg.zipte_gdg.api.service.member;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.service.member.response.oauth2.NaverResponse;
import org.gdg.zipte_gdg.api.service.member.response.oauth2.OAuth2UserResponse;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.member.MemberRepository;
import org.gdg.zipte_gdg.domain.member.MemberRole;
import org.gdg.zipte_gdg.domain.member.oauth2.CustomOAuth2User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;  // PasswordEncoder 주입

    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(request);
        log.info(oAuth2User.getAttributes().toString());

        String registrationId = request.getClientRegistration().getRegistrationId();
        OAuth2UserResponse oAuth2UserResponse = null;
        String email = null;
        String username = null;
        String phoneNumber = null;

        if (registrationId.equals("naver")) {
             oAuth2UserResponse = new NaverResponse(oAuth2User.getAttributes());

            email = oAuth2UserResponse.getEmail();
            username = oAuth2UserResponse.getProvider() + " " + oAuth2UserResponse.getName();
            phoneNumber = oAuth2UserResponse.getPhoneNumber();

        }

        // 기존 멤버 있는 지 파악
        Member existsmember = memberRepository.findByUsername(username);
        if (existsmember == null) {

            log.info("[Mylog]:" + oAuth2UserResponse.toString());

            String encodedPassword = passwordEncoder.encode("defaultPassword");  // 비밀번호 암호화

            Member member = Member.builder()
                    .email(email)
                    .username(username)
                    .phoneNumber(phoneNumber)
                    .password(encodedPassword)  // 암호화된 비밀번호 설정
                    .memberRole(MemberRole.GREEN)
                    .createdAt(LocalDateTime.now())
                    .build();

            memberRepository.save(member);
        } else {
            existsmember.changeEmail(oAuth2UserResponse.getEmail());
            memberRepository.save(existsmember);
        }

        String role = "ROLE_Green";
        return new CustomOAuth2User(oAuth2UserResponse, role);

    }





}
