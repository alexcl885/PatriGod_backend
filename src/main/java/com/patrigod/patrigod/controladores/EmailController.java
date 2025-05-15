package com.patrigod.patrigod.controladores;

import com.patrigod.patrigod.servicios.ServiEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private ServiEmail serviEmail;
    /**
     * Va perfecto!
     * http://localhost:8080/send-email?to=alexcopado2005@gmail.com
     * @param to persona a quien se le va a enviar el email
     * @return un envio de email a una persona
     */
    @GetMapping("/send-email")
    public String sendNewPersonEmail(@RequestParam String to) {
        try {
            boolean enviado = serviEmail.sendEmail(to, "Prueba Resend", "<h1>Hola desde Spring Boot</h1><p>Este es un email de prueba.</p>");
            return enviado ? "Email enviado con éxito!" : "Error al enviar email";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
