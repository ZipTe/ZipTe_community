package org.gdg.zipte_gdg.security.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.security.oauth.domain.CustomUser;
import org.gdg.zipte_gdg.security.oauth.domain.PrincipalDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.core.OAuth2ErrorCodes.INVALID_TOKEN;

@RequiredArgsConstructor
@Component
@Log4j2
public class TokenProvider {

    @Value("${jwt.key}")
    private String key;
    private SecretKey secretKey;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60L * 24 * 7;
    private static final String KEY_ROLE = "role";

    @PostConstruct
    private void setSecretKey() {
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, ACCESS_TOKEN_EXPIRE_TIME);
    }

    // 1. refresh token 발급
    public void generateRefreshToken(Authentication authentication, String accessToken) {
        String refreshToken = generateToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
    }

    private String generateToken(Authentication authentication, long expireTime) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + expireTime);

        // 권한 리스트 추출
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(KEY_ROLE, authorities)
                .claim("userId", principalDetails.getId())
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        // Integer를 Long으로 변환
        Long userId = Long.valueOf(claims.get("userId").toString());

        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        log.info("로그결과 "+ authorities);
        // 2. security의 User 객체 생성
        User principal = new CustomUser(claims.getSubject(), "", authorities,userId);

        log.info("Claims: " + claims);
        log.info("Roles: " + claims.get(KEY_ROLE, List.class));

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        // KEY_ROLE이 담고 있는 여러 권한을 List<String>로 가져옴
        List<String> roles = claims.get(KEY_ROLE, List.class);
        log.info("[MYLOG] " + roles);
        // 각 권한을 SimpleGrantedAuthority 객체로 변환하여 리스트로 반환
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }



    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        Claims claims = parseClaims(token);
        return claims.getExpiration().after(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            // JWT 파서를 빌드하고 서명된 토큰을 파싱
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)  // 서명 키를 설정
                    .build()
                    .parseClaimsJws(token)  // 서명된 JWT 토큰을 파싱
                    .getBody();  // Claims 객체 반환
        } catch (ExpiredJwtException e) {
            // 만료된 JWT 토큰의 경우 만료된 Claims 반환
            return e.getClaims();
        } catch (MalformedJwtException e) {
            // 잘못된 JWT 토큰 형식일 경우 예외 처리
            throw new CustomJWTException(INVALID_TOKEN);
        }
    }

}