package com.patrigod.platform.configurations.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.resend.Resend;

/**
 * Spring configuration for the Resend client (email sending service).
 * - Reads the Resend API key from the application properties.
 * - Exposes a Resend bean to be injected and used in email services.
 */
@Configuration
public class ResendConfiguration {

    @Value("${resend.api.key}")
    private String resendApiKey;

    /**
     * Creates and exposes a Resend bean configured with the API key.
     * @return a ready-to-use Resend instance
     */
    @Bean
    public Resend resendClient() {
        return new Resend(resendApiKey);
    }
}
