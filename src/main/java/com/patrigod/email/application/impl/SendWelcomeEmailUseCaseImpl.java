package com.patrigod.email.application.impl;

import com.patrigod.email.application.BaseTemplateUseCase;
import com.patrigod.email.application.SendEmailUseCase;
import com.patrigod.email.application.SendWelcomeEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendWelcomeEmailUseCaseImpl implements SendWelcomeEmailUseCase {

    private final SendEmailUseCase sendEmailUseCase;

    private final BaseTemplateUseCase baseTemplateUseCase;

    @Override
    public boolean sendWelcomeEmail(String to) {
        String subject = "¡Bienvenido a Patrigod!";
        String htmlContent = baseTemplateUseCase.baseTemplate("""
            <h2>Gracias por registrarte 🎉</h2>
            <p>Estamos encantados de tenerte con nosotros. A partir de ahora estarás al tanto de todas las novedades de la aplicación.</p>
        """);
        return sendEmailUseCase.sendEmail(to, subject, htmlContent);
    }
}
