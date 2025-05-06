package io.github.lokiwooooo.jwt;

import io.github.lokiwooooo.config.JwtProperties;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import io.github.lokiwooooo.userdetail.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class JwtTokenProvider {

    JwtProperties jwtProperties;
    CustomUserDetailsService customUserDetailsService;

    // Token 생성
    public JwtTokenResponse generateToken(final Authentication authentication) {
        log.info("### JWT Generate Token ###");

        // Step1. 권한 정보를 문자열 리스트로 변환
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Step.2 username기반으로 데이터를 갖고옴
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(authentication.getName());

        // Step3. 클레임에 username, roles 등 필요한 정보 추가
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", customUserDetails.getUserId());
        claims.put("userName", authentication.getName()); // 또는 .getPrincipal()에서 username 추출
        claims.put("email", customUserDetails.getEmail());
        claims.put("roles", roles);
//        claims.put("isAdmin", roles.stream()
//                .anyMatch(auth -> RoleType.ADMIN.toString().equals(auth.toUpperCase())));
//        claims.put("isEnable", customUserDetails.getIsEnabled());

        String accessToken = Jwts.builder()
                .setIssuer(jwtProperties.issuer())
                .setSubject(authentication.getName())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationMs()))
                .signWith(getSigningKey())
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuer(jwtProperties.issuer())
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpirationMs()))
                .signWith(getSigningKey())
                .compact();

        return new JwtTokenResponse(accessToken, refreshToken);
    }

    // AccessToken 생성
    public String generateAccessToken(final CustomUserDetails customUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", customUserDetails.getUserId());
        claims.put("userName", customUserDetails.getUsername()); // 또는 .getPrincipal()에서 username 추출
        claims.put("email", customUserDetails.getEmail());
        claims.put("roles", customUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
//        claims.put("isAdmin", customUserDetails.getAuthorities().stream()
//                .anyMatch(auth -> RoleType.ADMIN.equals(auth.getAuthority())));
//        claims.put("isEnable", UserStatus.ACTIVE.equals(customUserDetails.getIsEnabled()));

        return Jwts.builder()
                .addClaims(claims)
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationMs()))
                .signWith(getSigningKey())
                .compact();
    }

    // RefreshToken 생성
    public String generateRefreshToken(final CustomUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationMs()))
                .signWith(getSigningKey())
                .compact();
    }

    public Authentication getAuthentication(final String token) {
        Claims claims = parseClaims(token);

        List<String> roles = claims.get("roles", List.class);
        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        CustomUserDetails principal = CustomUserDetails.builder()
                .username(claims.getSubject())
                .email(claims.get("email", String.class))
                .userId(claims.get("userId", String.class))
//                .isAdmin(claims.get("isAdmin", Boolean.class))
//                .isEnabled(claims.get("isEnable", Boolean.class))
                .authorities((List<GrantedAuthority>) authorities)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("### JWT Validate Token Error ###");
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
    }

    // JWT의 특정 클래임을 갖고옴
    public String getUsernameFromToken(final String token) {
        // Claims 추출
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        // username이 subject(sub)에 들어있다면
        return claims.getSubject();

        // 만약 username을 별도 claim("username")에 저장했다면
        // return claims.get("username", String.class);
    }

}
