package com.example.stock.common.jwt;

import com.example.stock.service.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final static String SECRET_KEY = "abcdefg1234567890qwerasdfzxcv1234567890abcdefg1234567890qwerasdfzxcv1234567890";
    private final static long VALID_TIME = 3 * 60 * 1000L;

    private final UserService userService;

    public String createToken(String userEmail) {
        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("roles", Arrays.asList("ADMIN"));

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + VALID_TIME)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String resolveToken(HttpServletRequest request, String headerName) {
        String token = request.getHeader(headerName);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return token;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(this.getUserPk(token));
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return auth;
    }

    public String getUserPk(String token) {
        String userPk = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
        return userPk;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
