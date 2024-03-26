package com.hanyang.dataportal.core.jwt.component;

import com.hanyang.dataportal.core.exception.JwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.hanyang.dataportal.core.response.ResponseMessage.INVALID_JWT;

@Component
@RequiredArgsConstructor
public class JwtTokenResolver {

    private final JwtSecretKey jwtSecretKey;

    /**
     * 액세스 토큰을 복호화하는 메서드
     * @param accessToken
     * @return
     */
    private Claims parseToken(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey.getKey())
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    /**
     * 클레임에서 권한 정보를 꺼내오는 메서드
     * @param claims
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
        return Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * 복호화한 액세스 토큰을 검증하는 메서드
     * @param claims
     */
    private void validateClaims(Claims claims) {
        if (claims.get("auth") == null) {
            throw new JwtTokenException(INVALID_JWT);
        }
    }

    /**
     * JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
     * @param accessToken
     * @return
     */
    public Authentication getAuthentication(String accessToken) {
        final Claims claims = parseToken(accessToken);
        validateClaims(claims);

        final Collection<? extends GrantedAuthority> authorities = getAuthorities(claims);

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
}
