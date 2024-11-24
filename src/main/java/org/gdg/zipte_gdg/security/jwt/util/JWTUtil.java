package org.gdg.zipte_gdg.security.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class JWTUtil {

    @Value("${jwt.secret}")
    private String key;

    // 토큰 생성 메서드
    public String generateToken(Map<String, Object> valueMap, int min) {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        return Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(secretKey)
                .compact();
    }

    // 토큰 검증 및 클레임 반환
    public Map<String, Object> validateToken(String token) throws CustomJWTException {
        try {
            Claims claims = parseToken(token);
            return claims;
        } catch (MalformedJwtException e) {
            throw new CustomJWTException("Malformed");
        } catch (ExpiredJwtException e) {
            throw new CustomJWTException("Expired");
        } catch (JwtException e) {
            throw new CustomJWTException("JWT Error");
        }
    }

    // 토큰에서 클레임 파싱
    private Claims parseToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 클레임에서 유저 이름 추출
    public String getUsername(String token) {
        return parseToken(token).get("username", String.class);
    }

    public String getEmail(String token) {
        return parseToken(token).get("email", String.class);
    }

    public Long getUserId(String token) {
        return parseToken(token).get("userId", Long.class);

    }

    public List<String> getRoles(String token) {
        return parseToken(token).get("roles", List.class);
    }

    public Boolean isExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }
}
