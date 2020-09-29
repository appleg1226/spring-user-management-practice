package com.chong.usermanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
@Log
public class JwtTokenProvider {

    @Value("${security.secretKey}")
    private String secretKey;

    // 토큰의 유효기간(30분)
    final static long tokenValidTime = 30 * 60 * 1000L;
    private final UserDetailsService userDetailsService;

    @PostConstruct   // secret key를 인코딩
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * @param userPk    client의 아이디
     * @return  생성된 토큰을 반환
     */
    public String createToken(String userPk){
        Claims claims = Jwts.claims().setSubject(userPk);   // 토큰 사용하려는 데이터가 여기에 담긴다.

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * @param token JWT 토큰
     * @return      UserPK 값
     * Secret키를 넣어주고, token을 parse하여 원래 넣었던 userPK 값(클라이언트 아이디)를 얻어낸다.
     */
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * @param request   servlet request
     * @return  XAUTHTOKEN 값을 반환한다.
     * http 요청에서 token을 얻어낸다.
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    /**
     * @param token JWT 토큰
     * @return  Spring 자체 인증에 필요한 authentication 객체를 DB를 통하여 반환한다.
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * @param jwtToken  JWT 토큰
     * @return boolean 유효기간 이전인지 이후인지 확인하여 사용 가능하면 true를 반환한다.
     */
    public boolean validateToken(String jwtToken){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        return !claimsJws.getBody().getExpiration().before(new Date());
    }


}
