 package com.todos.backend.auth.jwt;
 
 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.SignatureAlgorithm;
 import io.jsonwebtoken.security.Keys;
 import java.util.Date;
 import org.springframework.stereotype.Service;
 
 @Service
 public class JwtService {
   private static final String SECRET_KEY = "12345678901234567890123456789012";
   
   public String generateToken(Integer id) {
     long expirationMs = 86400000L;
     
     return Jwts.builder()
       .setSubject(id.toString())
       .setIssuedAt(new Date())
       .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
       .signWith(Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes()), SignatureAlgorithm.HS256)
       .compact();
   }
   
   public String extractUserId(String token) {
     return ((Claims)Jwts.parserBuilder()
       .setSigningKey(Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes()))
       .build()
       .parseClaimsJws(token)
       .getBody())
       .getSubject();
   }
   
   public boolean validateToken(String token) {
     try {
       Jwts.parserBuilder()
         .setSigningKey(Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes()))
         .build()
         .parseClaimsJws(token);
       return true;
     } catch (Exception e) {
       return false;
     } 
   }
 }


