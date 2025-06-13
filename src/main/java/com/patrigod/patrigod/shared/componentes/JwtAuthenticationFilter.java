package com.patrigod.patrigod.shared.componentes;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.patrigod.patrigod.usuario.service.ServiDetalleUsuario;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired 
    ServiDetalleUsuario servicioDetalleUsuario;

    /**
     * Filtro que intercepta cada petición HTTP para comprobar la validez del JWT.
     * Si el token es válido, establece la autenticación en el contexto de seguridad de Spring.
     *
     * @param request  la petición HTTP entrante
     * @param response la respuesta HTTP saliente
     * @param chain    el filtro de la cadena
     * @throws ServletException en caso de error de servlet
     * @throws IOException en caso de error de IO
     */
    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain chain) throws ServletException, IOException {
            
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwtToken);
            } catch (ExpiredJwtException e) {
                System.out.println("Token expirado: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = servicioDetalleUsuario.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
