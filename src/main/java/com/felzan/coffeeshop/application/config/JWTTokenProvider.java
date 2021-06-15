package com.felzan.coffeeshop.application.config;

import com.felzan.coffeeshop.application.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {

  private final UserAuthDetailsService userAuthDetailsService;
  @Value("${jwt.secret}")
  private String jwtSecret;
  @Value("${jwt.expirationInMs}")
  private long jwtExpirationInMs;

  @PostConstruct
  protected void init() {
    jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
  }

  public String generateToken(UserDTO userDTO) {
    Claims claims = Jwts.claims().setSubject(userDTO.getUsername());
    Date now = new Date();
    Date expiraiton = new Date(now.getTime() + jwtExpirationInMs);

    Key key = getKey();
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiraiton)
        .signWith(key)
        .compact();
  }

  private Key getKey() {
    return new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userAuthDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody()
        .getSubject();
  }

  public String resolveToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
      return token.startsWith("Bearer ") ? token.substring(7) : token;
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(getKey())
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new RuntimeException("Expired or invalid JWT token");
    }
  }

}
