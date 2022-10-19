package com.web202230.ms_auth.jwt;

import com.web202230.ms_auth.entity.MainUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration*1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String getNombreUserFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch (MalformedJwtException e){
            logger.error("Token generado incorrectamente.");
        }
        catch (UnsupportedJwtException e){
            logger.error("Token generado no soportado.");
        }
        catch (ExpiredJwtException e){
            logger.error("Token expirado.");
        }
        catch (IllegalArgumentException e){
            logger.error("Token vacio.");
        }
        catch (SignatureException e){
            logger.error("Error al generar la firma.");
        }
        return false;
    }

}
