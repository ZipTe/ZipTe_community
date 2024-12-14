package org.gdg.zipte_gdg.security.jwt.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.security.jwt.util.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private static final String RETURN_URL = "http://localhost:3000";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Access Token 발급
        String accessToken = tokenProvider.generateAccessToken(authentication);

        // Redirect 및 쿠키 추가
        response.sendRedirect(RETURN_URL);
        response.addCookie(createCookie("Authorization", accessToken));

    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 3); // 3시간 유효기간 설정
        cookie.setPath("/");
        cookie.setHttpOnly(true); // HTTP-only 설정
        // cookie.setSecure(true); // HTTPS를 통한 접근 시 활성화

        return cookie;
    }
}
