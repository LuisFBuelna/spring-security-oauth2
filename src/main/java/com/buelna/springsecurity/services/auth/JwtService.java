package com.buelna.springsecurity.services.auth;

import com.buelna.springsecurity.entities.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final Logger log = LoggerFactory.getLogger(JwtService.class);

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime());

        String jwt = Jwts.builder()
                .claims().add(extraClaims)
                .and().subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .header().add("typ", "JWT")
                .and().signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    private Key generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        log.info("El SECRET_KEY ES = {}", SECRET_KEY);
        log.info("La key decodificada es = {}", new String(passwordDecoded, StandardCharsets.UTF_8));
        return Keys.hmacShaKeyFor(passwordDecoded);
    }
}
