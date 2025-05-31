package com.patrigod.patrigod.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de CORS para la aplicación Spring Boot.
 * 
 * Permite que el frontend (por ejemplo, en Vite en http://localhost:5173)
 * pueda hacer peticiones al backend en /api/**, incluyendo credenciales y todos los métodos HTTP.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Configura las reglas de CORS para las rutas de la API.
     * Permite solicitudes desde el origen especificado, con cualquier cabecera y métodos comunes.
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
