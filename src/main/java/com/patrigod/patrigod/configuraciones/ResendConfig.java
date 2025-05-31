package com.patrigod.patrigod.configuraciones;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.resend.Resend;

/**
 * Configuración de Spring para el cliente de Resend (servicio de envío de emails).
 * 
 * - Lee la clave API de Resend desde las propiedades de la aplicación.
 * - Expone un bean de tipo Resend para ser inyectado y utilizado en los servicios de email.
 */
@Configuration
public class ResendConfig {
    @Value("${resend.api.key}")
    private String resendApiKey;

    /**
     * Crea y expone un bean de tipo Resend configurado con la clave API.
     * @return instancia de Resend lista para usar
     */
    @Bean
    public Resend resendClient() {
        return new Resend(resendApiKey);
    }
}
