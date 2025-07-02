package com.patrigod.email.infraestructure.controller;

import com.patrigod.email.application.SendEmailUseCase;
import com.patrigod.email.application.SendUpdateEmailUseCase;
import com.patrigod.email.application.SendWelcomeEmailUseCase;
import com.patrigod.email.infraestructure.controller.dto.input.UpdateMessageRequestDto;
import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.messages.InformationMessages;
import com.patrigod.usuario.entity.entity.Usuario;
import com.patrigod.usuario.service.ServiUsuario;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final SendEmailUseCase sendEmailUseCase;
    private final SendWelcomeEmailUseCase sendWelcomeEmailUseCase;
    private final SendUpdateEmailUseCase sendUpdateNotification;

    private final ServiUsuario serviUsuario;

    /**
     * Enviar un correo de prueba con Resend.
     * Ejemplo: http://localhost:8080/api/email/send-email?to=ejemplo@gmail.com
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
     * Envía un correo de bienvenida a un usuario recién registrado.
     */
    @PostMapping("usuario/registro/{email}")
    public ResponseEntity<String> registrarUsuario(@PathVariable String email) {
        boolean send = sendWelcomeEmailUseCase.sendWelcomeEmail(email);
        if (send) {
            return ResponseEntity.ok(InformationMessages.EMAIL_SEND);
        } else {
            return ResponseEntity.status(500).body(ErrorMessages.SEND_EMAIL_ERROR);
        }
    }
    /**
     * 
     * @param request mensaje del admin al que mandara a todos los usuarios
     * @return los correos que ha podido enviar desde resend.com
     */
    @PostMapping("admin/actualizacion")
    public ResponseEntity<String> emailActualizacionPatriGod(@RequestBody UpdateMessageRequestDto request) {
        List<Usuario> users = serviUsuario.findTipoUsuario();
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
