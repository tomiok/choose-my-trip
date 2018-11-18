package com.tomiok.authservice.infra;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {

  public static String createJwtFromUsername(String username) {
    return Jwts
        .builder()
        .setSubject(username)
        .setIssuedAt(new Date(Instant.now().toEpochMilli()))
        .setId(UUID.randomUUID().toString())
        .signWith(SignatureAlgorithm.HS512, "secret") //TODO change this in prod
        .compact();
  }
}
