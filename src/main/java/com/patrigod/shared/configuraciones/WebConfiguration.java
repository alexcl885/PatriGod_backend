package com.patrigod.shared.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global CORS configuration for the Spring Boot application.
 * Allows the frontend
 * to make requests to the backend at /api/**, including credentials and all HTTP methods.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * Configures the CORS rules for the API routes.
     * Allows requests from the specified origin, with any headers and common methods.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Ruta del backend
                .allowedOrigins("http://localhost:5173") // Frontend en Vite
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
