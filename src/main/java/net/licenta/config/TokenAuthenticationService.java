package net.licenta.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.licenta.Constants;

public class TokenAuthenticationService {

  void addAuthentication(HttpServletResponse res, String username, Collection<? extends GrantedAuthority> authorities) {
    String jwt = Jwts.builder().setSubject(username).claim("authority", authorities).setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, Constants.SECRET).compact();
    res.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + " " + jwt);
  }

  Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(Constants.HEADER_STRING);

    if (token != null) {
      String user = null;
      Jws<Claims> claims = null;
      try {
        claims = Jwts.parser().setSigningKey(Constants.SECRET).parseClaimsJws(token.replaceAll(Constants.TOKEN_PREFIX, ""));
        user = claims.getBody().getSubject();
      }
      catch (ExpiredJwtException e) {
        return null;
      }

      Collection<? extends GrantedAuthority> authority = Arrays.asList(claims.getBody().get("authority").toString()).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

      return user != null ? new UsernamePasswordAuthenticationToken(user, null, authority) : null;
    }
    return null;
  }
}
