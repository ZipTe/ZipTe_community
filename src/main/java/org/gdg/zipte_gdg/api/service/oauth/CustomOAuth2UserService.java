package org.gdg.zipte_gdg.api.service.oauth;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.service.oauth.response.NaverResponse;
import org.gdg.zipte_gdg.api.service.oauth.response.OAuth2UserResponse;
import org.gdg.zipte_gdg.api.service.oauth.response.UserDTO;
import org.gdg.zipte_gdg.domain.member.Address;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.member.MemberRepository;
import org.gdg.zipte_gdg.domain.oauth2.PrincipalDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;  // PasswordEncoder 주입

    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        // 정보 가져오기
        OAuth2User oAuth2User = super.loadUser(request);

        // naver,google같은 소셜 정보
        String registrationId = request.getClientRegistration().getRegistrationId();

        OAuth2UserResponse oAuth2UserResponse = null;
        String email = null;
        String username = null;
        String phoneNumber = null;

        // 네이버
        if (registrationId.equals("naver")) {
             oAuth2UserResponse = new NaverResponse(oAuth2User.getAttributes());

            email = oAuth2UserResponse.getEmail();
            username = oAuth2UserResponse.getProvider() + " " + oAuth2UserResponse.getName();
            phoneNumber = oAuth2UserResponse.getPhoneNumber();

        }
        // -- 구글 추가 예정 --

        // -- 카카오 추가 예정

        // 기존 멤버 있는 지 파악
        Member existsmember = memberRepository.findByEmail(email);
//        log.info("[Mylog]:" + email);

        if (existsmember == null) {

//            log.info("[Mylog]:" + oAuth2UserResponse.toString());

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode("defaultPassword");
            Address address = Address.newAddress("미정", "미정", 11111);
            Member member = Member.createNewMember(email, username, encodedPassword, phoneNumber, address);
            memberRepository.save(member);

            UserDTO userDTO = UserDTO.builder()
                    .email(email)
                    .username(username)
                    .role("ROLE_GREEN")
                    .build();

            return new PrincipalDetails(userDTO);
        } else {
            existsmember.changeEmail(oAuth2UserResponse.getEmail());
            Member save = memberRepository.save(existsmember);

            UserDTO userDTO = UserDTO.builder()
                    .email(email)
                    .username(username)
                    .role(String.valueOf(save.getMemberRole()))
                    .build();

            return new PrincipalDetails(userDTO);
        }

    }





}
