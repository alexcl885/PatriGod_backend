package com.patrigod.patrigod.componentes;

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

import com.patrigod.patrigod.servicios.ServiDetalleUsuario;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired 
    ServiDetalleUsuario servicioDetalleUsuario;

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

        // 🔍 Agrega este log para ver qué rol se extrae del token
        System.out.println("Rol extraído del token: " + jwtUtil.extractRole(jwtToken));

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
