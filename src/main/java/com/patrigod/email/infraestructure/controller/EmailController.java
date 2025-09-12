package com.patrigod.email.infraestructure.controller;

import com.patrigod.email.application.SendEmailUseCase;
import com.patrigod.email.application.SendUpdateEmailUseCase;
import com.patrigod.email.application.SendWelcomeEmailUseCase;
import com.patrigod.email.infraestructure.controller.dto.input.UpdateMessageRequestDto;
import com.patrigod.exception.messages.ErrorMessages;
import com.patrigod.exception.messages.InformationMessages;
import com.patrigod.user.application.GetUserTypeUseCase;
import com.patrigod.user.domain.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO 5 > Encontrar otra menera para enviar correos o dejar esta porque si...
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final SendEmailUseCase sendEmailUseCase;
    private final SendWelcomeEmailUseCase sendWelcomeEmailUseCase;
    private final SendUpdateEmailUseCase sendUpdateNotification;

    private final GetUserTypeUseCase getUserTypeUseCase;


    /**
     * Send a test email using Resend.
     * Example: http://localhost:8080/api/email/send-email?to=example@gmail.com
     */
    @GetMapping("usuario/send-email")
    public ResponseEntity<String> sendNewPersonEmail(@RequestParam String to) {
        boolean enviado = sendEmailUseCase.sendEmail(to, "Prueba Resend",
                "<h1>Hola desde Spring Boot</h1><p>Este es un email de prueba.</p>");
        if (enviado) {
            return ResponseEntity.ok(InformationMessages.EMAIL_SEND);
        } else {
            return ResponseEntity.status(500).body(ErrorMessages.SEND_EMAIL_ERROR);
        }
    }

    /**
     * Sends a welcome email to a newly registered user.
     */
    @PostMapping("usuario/registro/{email}")
    public ResponseEntity<String> sendWelcomeUser(@PathVariable String email) {
        boolean send = sendWelcomeEmailUseCase.sendWelcomeEmail(email);
        if (send) {
            return ResponseEntity.ok(InformationMessages.EMAIL_SEND);
        } else {
            return ResponseEntity.status(500).body(ErrorMessages.SEND_EMAIL_ERROR);
        }
    }

    /**
     *
     * @param request message from the admin to be sent to all users
     * @return the emails that were successfully sent via resend.com
     */
    @PostMapping("admin/actualizacion")
    public ResponseEntity<String> emailActualizacionPatriGod(@RequestBody UpdateMessageRequestDto request) {
        List<User> users = getUserTypeUseCase.getUserType();
        /*  
            Esto seria para un plan mejor con resend.com
            para que pueda enviar correos a to-do el mundo

        int enviados = 0;
        for (Usuario usuario : usuarios) {
            boolean enviado = serviEmail.sendUpdateNotification(usuario.getEmail(), request.getUpdateMessage());
            if (enviado) enviados++;
        }
        */
        /**
         * Como tengo la version gratis solo puede ser a alexcopado2005@gmail.com
         */
        int sends = 1;
        sendUpdateNotification.sendUpdateEmail("alexcopado2005@gmail.com", request.getUpdateMessage());
        return ResponseEntity.ok("Correos enviados: " + sends + " de " + users.size());
    }


}
