package com.example.SpringBootTutorial.Jwtauth.security.service;

import com.example.SpringBootTutorial.Jwtauth.model.User;
import com.example.SpringBootTutorial.Jwtauth.model.properties.security.SecurityProperties;
import com.example.SpringBootTutorial.Jwtauth.service.base.TokenGenerator;
import com.example.SpringBootTutorial.Jwtauth.service.base.TokenReader;
import com.example.SpringBootTutorial.Jwtauth.util.PublicAndPrivateKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenManager implements TokenGenerator<User> , TokenReader<Claims> {
//Learn:
// Single Responsibility Principle (SRP)
// Flexibility: Components that interact with your AccessTokenManager can choose to use only the TokenGenerator or only the TokenReader interface based on their requirements. This flexibility can be beneficial when integrating with other parts of the system.

    private final SecurityProperties securityProperties;
    @Override
    public String generate(User obj) {
        Claims claims = Jwts.claims();
        claims.put("username" ,obj.getUsername() );
        claims.put("name",obj.getName());

        Date now = new Date();
//        Date expDate = new Date(now.getTime() + securityProperties.getJwt().getAccessTokenValidityTime() );

        return Jwts.builder()
                .addClaims(claims)
//                .setExpiration(expDate)
                .setIssuedAt(now)
                .setSubject(String.valueOf(obj.getId()))
                .signWith(PublicAndPrivateKey.getPrivateKey() , SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public Claims read(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(PublicAndPrivateKey.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

//        if (claims.containsKey("type") ){
//            if(claims.get("type").equals(REFRESH_TOKEN_TYPE)){

//                throw new RuntimeException("This is refresh token , you must send access token");
//            }
//        }

        return claims;
    }
}
