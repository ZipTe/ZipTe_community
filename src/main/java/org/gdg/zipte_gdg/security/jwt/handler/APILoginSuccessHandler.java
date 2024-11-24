package org.gdg.zipte_gdg.security.jwt.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.security.oauth.domain.PrincipalDetails;
import org.gdg.zipte_gdg.security.jwt.util.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;


@Log4j2
@RequiredArgsConstructor
@Component
public class APILoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        Map<String, Object> valueMap = getStringObjectMap(authentication, principalDetails);

        String token = jwtUtil.generateToken(valueMap, 60);

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:8080/api/member/myPage");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.write(new Gson().toJson(valueMap));
//        printWriter.write(token);
//        printWriter.close();
    }

    private static Map<String, Object> getStringObjectMap(Authentication authentication, PrincipalDetails principalDetails) {
        String username = principalDetails.getUsername();
        String email = principalDetails.getEmail();
        Long userId = principalDetails.getId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 모든 권한을 문자열 리스트로 변환
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("username", username);
        valueMap.put("email", email);
        valueMap.put("userId", userId);
        valueMap.put("roles", roles); // 역할 목록 저장
        return valueMap;
    }


    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*3);
        // Https를 통한 접근
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
