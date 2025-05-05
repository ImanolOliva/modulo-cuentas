package com.example.cuentas.modulo.cuentas.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {


    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;


    //todo: Metodo que se encarga de generar token de acceso
    public String generateAcessToken(String username){
        //todo: Codifica el token y lo genera;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //todo:Validar el token de acceso
    public boolean isTokenValid(String token){
        try{
            //todo: lee el token
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        }catch (Exception e){
                log.error("Token invalido, error:".concat(e.getMessage()));
                return false;
        }
    }



    //todo: Obtener todos los claims(informacion) del token (lo que viaja en el payload)
    public Claims extractAllClaims(String token){
        //todo: lee el token
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //todo: Obtener el username del token;
    public String getUsernameFromToken(String token){
        return getClaim(token,Claims::getSubject);
    }


    //todo: Obtener un solo Claims
    public <T> T getClaim(String token, Function<Claims,T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    //todo: Obtener firma del token, y lo codifica
    public Key getSignatureKey(){
        byte[] keyBates = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBates);
    }

}
