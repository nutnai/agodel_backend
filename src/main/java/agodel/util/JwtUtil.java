package agodel.util;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    private static String secret;

    @Value("${jwt.secret.key}")
    private void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    public static String generateToken(String id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        return doGenerateToken(claims);
    }

    private static String doGenerateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), Jwts.SIG.HS256)
                .compact();
    }

    public static Object validateToken(String token) {
        //validate token by check token's signature and secret key is the same
        
        try {
            Object t = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token).getPayload();
            Map<String, Object> result = new HashMap<>();
            result.put("isValid", true);
            result.put("id", ((Map<String, Object>) t).get("id"));
            return result;
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            result.put("isValid", false);
            result.put("error", e.getMessage());
            return result;
        }
    }
}