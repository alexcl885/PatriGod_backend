package com.patrigod.email.application.impl;

import com.patrigod.email.application.EmailTemplateUseCase;
import com.patrigod.email.application.SendEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendUpdateEmailUseCase implements com.patrigod.email.application.SendUpdateEmailUseCase {

    private final SendEmailUseCase sendEmailUseCase;

    private final EmailTemplateUseCase emailTemplateUseCase;

    @Override
    public boolean sendUpdateEmail(String to, String updateMessage) {
        String subject = "Updates from Patrigod 📰";
        String htmlContent = emailTemplateUseCase.baseTemplate(String.format("""
            <h3>We have news for you</h3>
            <p>%s</p>
        """, updateMessage));
        return sendEmailUseCase.sendEmail(to, subject, htmlContent);
    }
}
