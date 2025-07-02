package com.patrigod.email.application.impl;

import com.patrigod.email.application.BaseTemplateUseCase;
import com.patrigod.email.application.SendEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendUpdateEmailUseCase implements com.patrigod.email.application.SendUpdateEmailUseCase {

    private final SendEmailUseCase sendEmailUseCase;

    private final BaseTemplateUseCase baseTemplateUseCase;

    @Override
    public boolean sendUpdateEmail(String to, String updateMessage) {
        String subject = "Novedades en Patrigod 📰";
        String htmlContent = baseTemplateUseCase.baseTemplate(String.format("""
            <h3>Tenemos novedades para ti</h3>
            <p>%s</p>
        """, updateMessage));
        return sendEmailUseCase.sendEmail(to, subject, htmlContent);
    }
}
