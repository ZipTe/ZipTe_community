package org.gdg.zipte_gdg.security.handler;

import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.domain.member.oauth2.CustomOAuth2User;
import org.gdg.zipte_gdg.security.util.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Component
public class APILoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("username", username);
        valueMap.put("role", role);

        String token = jwtUtil.generateToken(valueMap, 60);

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:8080/api/review/list/apt/1");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.write(new Gson().toJson(valueMap));
//        printWriter.write(token);
//        printWriter.close();
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    // -------------------
    //    @Override
    //    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    //        log.info("-------Success Handler------");
    //        log.info(authentication);
    //
    //        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
    //
    //        Map<String, Object> claims = memberDTO.getClaims();
    //        claims.put("accessToken", JWTUtil.generateToken(claims,10));
    //        claims.put("refreshToken", JWTUtil.generateToken(claims,60*24));
    //
    //        //JSon문자열로
    //        Gson gson = new Gson();
    //        String jsonStr = gson.toJson(claims);
    //        response.setContentType("application/json;charset=UTF-8");
    //
    //        PrintWriter printWriter = response.getWriter();
    //        printWriter.write(jsonStr);
    //        printWriter.close();
    //    }
}
