package com.patrigod.email.service;

import org.springframework.stereotype.Service;
import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

@Service
public class ServiEmail {

    private final Resend resend;

    public ServiEmail(Resend resend) {
        this.resend = resend;
    }

    public boolean sendEmail(String to, String subject, String htmlContent) {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Patrigod <onboarding@resend.dev>")
                .to(to)
                .subject(subject)
                .html(htmlContent)
                .build();

        try {
            CreateEmailResponse response = resend.emails().send(params);
            System.out.println("Email enviado con ID: " + response.getId());
            return response.getId() != null;
        } catch (ResendException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendWelcomeEmail(String to) {
        String subject = "¡Bienvenido a Patrigod!";
        String htmlContent = getBaseTemplate("""
            <h2>Gracias por registrarte 🎉</h2>
            <p>Estamos encantados de tenerte con nosotros. A partir de ahora estarás al tanto de todas las novedades de la aplicación.</p>
        """);
        return sendEmail(to, subject, htmlContent);
    }

    public boolean sendUpdateNotification(String to, String updateMessage) {
        String subject = "Novedades en Patrigod 📰";
        String htmlContent = getBaseTemplate(String.format("""
            <h3>Tenemos novedades para ti</h3>
            <p>%s</p>
        """, updateMessage));
        return sendEmail(to, subject, htmlContent);
    }

    

    private String getBaseTemplate(String content) {
        return """
            <html>
                <body style='font-family: Arial, sans-serif; padding: 20px; background-color: #f9f9f9;'>
                    <div style='max-width: 600px; margin: auto; background-color: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px #ccc;'>
                        <h1 style='color: #004080;'>Patrigod</h1>
                        %s
                        <hr />
                        <p style='font-size: 12px; color: gray;'>Estás recibiendo este correo porque estás registrado en nuestra plataforma.</p>
                    </div>
                </body>
            </html>
        """.formatted(content);
    }
}
