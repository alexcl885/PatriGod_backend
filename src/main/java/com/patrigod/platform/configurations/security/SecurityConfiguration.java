package com.patrigod.platform.configurations.security;

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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.patrigod.platform.configurations.security.components.JwtAuthenticationFilter;
import com.patrigod.user.application.impl.DetailUserUseCaseImpl;

/**
 * Main security configuration for the Spring Boot application.
 *
 * - Defines authorization rules for the API routes.
 * - Configures JWT (JSON Web Token) based authentication.
 * - Sets the session policy to stateless.
 * - Defines the necessary beans for authentication and password encryption.
 *
 * Protected routes:
 *   - Admin only: /api/comida/**, /api/evento/**, /api/monumento/**, /api/admin/**
 *   - Authenticated users: /api/ollama/chat/**, /api/puntuacion/**
 *   - Public: /api/auth/**, /api/ciudad/**, /api/usuario/**, /api/email/**
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configures the security filter chain and authorization rules.
     * - Disables CSRF.
     * - Defines which routes require authentication, admin role, or are public.
     * - Adds the JWT filter before the username/password authentication filter.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Admins and user authenticated
                        .requestMatchers(
                                "/api/ollama/chat/**",
                                "/api/puntuacion/**")
                        .authenticated()

                        // router public
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/auth/*/**",
                                "/api/city/**",
                                "/api/user/**",
                                "/api/email/**",
                                "/swagger-ui/**",
                                "v3/api-docs/**")
                        .permitAll()
                        // only admin
                        .requestMatchers(
                                "/api/food/**",
                                "/api/event/**",
                                "/api/monument/**",
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
     * Bean for the AuthenticationManager, required for authentication.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean that provides the user details service.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new DetailUserUseCaseImpl();
    }

    /**
     * Bean that configures the authentication provider with the user service and password encoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Bean for the password encoder using BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
