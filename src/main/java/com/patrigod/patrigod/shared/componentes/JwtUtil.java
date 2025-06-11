package com.patrigod.patrigod.shared.componentes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "miClaveSuperSecretaQueNadieVaAAdivinarJamas";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 12; // 12 horas

    /**
     * Extrae el nombre de usuario (subject) del token JWT.
     * @param token el token JWT
     * @return el nombre de usuario contenido en el token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae el rol del usuario del token JWT.
     * @param token el token JWT
     * @return el rol contenido en el token
     */
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    /**
     * Extrae un claim específico del token JWT usando una función resolutora.
     * @param token el token JWT
     * @param claimsResolver función para obtener el claim deseado
     * @param <T> tipo del claim
     * @return el valor del claim extraído
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae todos los claims del token JWT.
     * @param token el token JWT
     * @return los claims contenidos en el token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Genera un token JWT para el usuario proporcionado, incluyendo su rol.
     * @param userDetails los detalles del usuario autenticado
     * @return el token JWT generado
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crea un token JWT con los claims y el subject proporcionados.
     * @param claims claims a incluir en el token
     * @param subject el subject (normalmente el nombre de usuario)
     * @return el token JWT generado
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Valida si el token JWT es válido para el usuario proporcionado.
     * @param token el token JWT
     * @param userDetails los detalles del usuario autenticado
     * @return true si el token es válido y no ha expirado, false en caso contrario
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Comprueba si el token JWT ha expirado.
     * @param token el token JWT
     * @return true si el token ha expirado, false en caso contrario
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
