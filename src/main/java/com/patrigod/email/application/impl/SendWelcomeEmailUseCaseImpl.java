package com.patrigod.email.application.impl;

import com.patrigod.email.application.EmailTemplateUseCase;
import com.patrigod.email.application.SendEmailUseCase;
import com.patrigod.email.application.SendWelcomeEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendWelcomeEmailUseCaseImpl implements SendWelcomeEmailUseCase {

    private final SendEmailUseCase sendEmailUseCase;

    private final EmailTemplateUseCase emailTemplateUseCase;

    @Override
    public boolean sendWelcomeEmail(String to) {
        String subject = "Welcome to Patrigod!";
        String htmlContent = emailTemplateUseCase.baseTemplate("""
            <h2>Thank you for signing up 🎉</h2>
            <p>We’re excited to have you with us. From now on, you’ll stay updated on all the latest news from the app.</p>
        """);
        return sendEmailUseCase.sendEmail(to, subject, htmlContent);
    }
}
