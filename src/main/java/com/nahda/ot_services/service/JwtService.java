package com.nahda.ot_services.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    // Replace this with a secure key in a real application, ideally fetched from environment variables
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    // Generate token with given user name
    public String generateToken(String userName, UUID userUuid,Map<String, Object> claims) {
        return createToken(claims, userName,userUuid);
    }

    // Create a JWT token with specified claims and subject (user name)
    private String createToken(Map<String, Object> claims, String userName, UUID userUuid) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setId(userUuid.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120)) // Token valid for 120 minutes
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 30)) // Token valid for 120 minutes
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key for JWT token
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the username from the token
    public UUID extractUserUuid(String token) {
        return UUID.fromString(extractClaim(token, Claims::getId));
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public List<GrantedAuthority> extractGroupUUID(String token) {
        Object permissionsClaim = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("GROUP");

        if (permissionsClaim instanceof List<?> permissionsList) {
            return permissionsList.stream()
                    .map(item -> {
                        if (item instanceof Map<?, ?> map) {
                            return new SimpleGrantedAuthority((String) map.get("authority")); // Extract authority field
                        }
                        return new SimpleGrantedAuthority(item.toString());
                    })
                    .collect(Collectors.toList());
        }

        return List.of();
    }

    public List<GrantedAuthority> extractRoles(String token) {
        Object permissionsClaim = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("ROLES");

        if (permissionsClaim instanceof List<?> permissionsList) {
            return permissionsList.stream()
                    .map(item -> {
                        if (item instanceof Map<?, ?> map) {
                            return new SimpleGrantedAuthority((String) map.get("authority")); // Extract authority field
                        }
                        return new SimpleGrantedAuthority(item.toString());
                    })
                    .collect(Collectors.toList());
        }

        return List.of();
    }

    public List<GrantedAuthority> extractPermissions(String token) {
        Object permissionsClaim = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("PERMISSIONS");

        if (permissionsClaim instanceof List<?> permissionsList) {
            return permissionsList.stream()
                    .map(item -> {
                        if (item instanceof Map<?, ?> map) {
                            return new SimpleGrantedAuthority((String) map.get("authority")); // Extract authority field
                        }
                        return new SimpleGrantedAuthority(item.toString());
                    })
                    .collect(Collectors.toList());
        }

        return List.of();
    }
}
