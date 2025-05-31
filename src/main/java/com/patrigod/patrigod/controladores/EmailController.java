package com.patrigod.patrigod.controladores;

import com.patrigod.patrigod.DTO.UpdateMessageRequestDTO;
import com.patrigod.patrigod.modelos.Usuario;
import com.patrigod.patrigod.servicios.ServiEmail;
import com.patrigod.patrigod.servicios.ServiUsuario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final ServiEmail serviEmail;
    private final ServiUsuario serviUsuario;


    public EmailController(ServiEmail serviEmail, ServiUsuario serviUsuario) {
        this.serviEmail = serviEmail;
        this.serviUsuario = serviUsuario;
    }
    

    /**
     * Enviar un correo de prueba con Resend.
     * Ejemplo: http://localhost:8080/api/email/send-email?to=ejemplo@gmail.com
     */
    @GetMapping("usuario/send-email")
    public ResponseEntity<String> sendNewPersonEmail(@RequestParam String to) {
        boolean enviado = serviEmail.sendEmail(to, "Prueba Resend",
                "<h1>Hola desde Spring Boot</h1><p>Este es un email de prueba.</p>");

        if (enviado) {
            return ResponseEntity.ok("Email enviado con éxito!");
        } else {
            return ResponseEntity.status(500).body("Error al enviar el email.");
        }
    }

    /**
     * Envía un correo de bienvenida a un usuario recién registrado.
     */
    @PostMapping("usuario/registro/{email}")
    public ResponseEntity<String> registrarUsuario(@PathVariable String email) {
        boolean enviado = serviEmail.sendWelcomeEmail(email);

        if (enviado) {
            return ResponseEntity.ok("Usuario registrado y correo enviado.");
        } else {
            return ResponseEntity.status(500).body("Usuario registrado pero error al enviar el correo.");
        }
    }
    /**
     * 
     * @param request mensaje del admin al que mandara a todos los usuarios
     * @return los correos que ha podido enviar desde resend.com
     */
    @PostMapping("admin/actualizacion")
    public ResponseEntity<String> emailActualizacionPatriGod(@RequestBody UpdateMessageRequestDTO request) {
    
        List<Usuario> usuarios = serviUsuario.findTipoUsuario(); 
        /*  
            Esto seria para un plan mejor con resend.com
            para que pueda enviar correos a todo el mundo

        int enviados = 0;
        for (Usuario usuario : usuarios) {
            boolean enviado = serviEmail.sendUpdateNotification(usuario.getEmail(), request.getUpdateMessage());
            if (enviado) enviados++;
        }
        */
        /**
         * Como tengo la version gratis solo puede ser a alexcopado2005@gmail.com
         */
        int enviados = 1;
        serviEmail.sendUpdateNotification("alexcopado2005@gmail.com", request.getUpdateMessage());
        return ResponseEntity.ok("Correos enviados: " + enviados + " de " + usuarios.size());
    }


}
