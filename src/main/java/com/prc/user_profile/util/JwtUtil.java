package com.prc.user_profile.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.security.KeyRep.Type.SECRET;

public class JwtUtil {
    private final String SECRET = "itsyourweb";
    private final long EXPIRATION_TIME = 1000 *60 *5;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
       List<String> role1 = Flux.fromIterable(userDetails.getAuthorities())
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_"))
                .map(role -> role.substring(5))
                .collectList().block();

        List<String> scopes = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("SCOPE_"))
                .map(auth -> auth.substring(6)) // remove SCOPE_
                .collect(Collectors.toList());
        claims.put("role",role1);
        claims.put("scope",scopes);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();

    }
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    public List<String> extractRoles(String token) {
        return extractAllClaims(token).get("roles", List.class);
    }

    public List<String> extractScopes(String token) {
        return extractAllClaims(token).get("scopes", List.class);
    }
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token); // will throw if invalid
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
