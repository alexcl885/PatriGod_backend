package com.patrigod.shared.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.patrigod.shared.componentes.JwtAuthenticationFilter;
import com.patrigod.usuario.service.ServiDetalleUsuario;

/**
 * Configuración principal de seguridad para la aplicación Spring Boot.
 * 
 * - Define las reglas de autorización para las rutas de la API.
 * - Configura la autenticación basada en JWT (JSON Web Token).
 * - Establece la política de sesión como stateless.
 * - Define los beans necesarios para la autenticación y el cifrado de contraseñas.
 * 
 * Rutas protegidas:
 *   - Solo administradores: /api/comida/**, /api/evento/**, /api/monumento/**, /api/admin/**
 *   - Usuarios autenticados: /api/ollama/chat/**, /api/puntuacion/**
 *   - Públicas: /api/auth/**, /api/ciudad/**, /api/usuario/**, /api/email/**
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configura la cadena de filtros de seguridad y las reglas de autorización.
     * - Desactiva CSRF.
     * - Define qué rutas requieren autenticación, rol de administrador o son públicas.
     * - Añade el filtro JWT antes del filtro de autenticación por usuario/contraseña.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Admins y usuarios autenticados
                        .requestMatchers(
                                "/api/ollama/chat/**",
                                "/api/puntuacion/**")
                        .authenticated()

                        // rutas públicas
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/auth/*/**",
                                "/api/ciudad/**",
                                "/api/usuario/**",
                                "/api/email/**",
                                "/swagger-ui/**",
                                "v3/api-docs/**")
                        .permitAll()
                        // solo administradores
                        .requestMatchers(
                                "/api/comida/**",
                                "/api/evento/**",
                                "/api/monumento/**",
                                "/api/admin/**")
                        .hasRole("ADMINISTRADOR")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .cors(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Bean para el AuthenticationManager, necesario para la autenticación.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean que proporciona el servicio de carga de detalles de usuario.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new ServiDetalleUsuario();
    }

    /**
     * Bean que configura el proveedor de autenticación con el servicio de usuarios y el codificador de contraseñas.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Bean para el codificador de contraseñas usando BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
