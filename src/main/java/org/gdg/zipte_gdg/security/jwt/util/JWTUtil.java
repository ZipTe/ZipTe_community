package org.gdg.zipte_gdg.security.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.member.MemberRepository;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class JWTUtil {

    private final MemberRepository memberRepository;

//    @Value("${jwt.secretKey}")
//    private static String key = "12345678901234567890123456789012";
    // 비밀 키를 안전하게 생성 (HS256 알고리즘에 맞게 자동 생성)
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 토큰 생성 메서드
    public static String generateToken(Map<String, Object> valueMap, int min) {

        // JWT 생성
        String jwtStr = Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))  // 헤더 설정
                .setClaims(valueMap)  // Claims 설정
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))  // 발급 시간
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))  // 만료 시간
                .signWith(key)  // 서명
                .compact();  // JWT 생성

        return jwtStr;
    }

    // 토큰 검증 및 클레임 반환
    public static Map<String, Object> validateToken(String token) throws CustomJWTException {

        Map<String, Object> claim = null;

        try {
            // JWT 파싱 및 검증
            claim = Jwts.parserBuilder()
                    .setSigningKey(key)  // 서명 키 설정
                    .build()
                    .parseClaimsJws(token)  // 토큰 파싱 및 검증
                    .getBody();  // 클레임 추출

        } catch (MalformedJwtException e) {
            throw new CustomJWTException("MalFormed");
        } catch (ExpiredJwtException e) {
            throw new CustomJWTException("Expired");
        } catch (InvalidClaimException e) {
            throw new CustomJWTException("Invalid");
        } catch (JwtException e) {
            throw new CustomJWTException("JWTError");
        } catch (Exception e) {
            throw new CustomJWTException("Error");
        }

        return claim;
    }

    public String getUsername(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("username", String.class);
    }

    public String getEmal(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("email", String.class);
    }

    public String getRole(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public Long getUserId (String token) {
        String email = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("email", String.class);
        Member member = memberRepository.findByEmail(email);
        return member.getId();
    }

    public Boolean isExpired(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}


