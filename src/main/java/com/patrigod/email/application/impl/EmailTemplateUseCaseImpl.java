package com.patrigod.email.application.impl;

import com.patrigod.email.application.EmailTemplateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailTemplateUseCaseImpl implements EmailTemplateUseCase {

    @Override
    public String baseTemplate(String content) {
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
