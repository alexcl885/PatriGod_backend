package com.patrigod.patrigod.servicios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ServiEmail {
    @Value("${resend.api.key}")
    private String resendApiKey;

    private static final String RESEND_URL = "https://api.resend.com/emails";

    public boolean sendEmail(String to, String subject, String htmlContent) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String jsonBody = String.format("""
            {
                "from": "onboarding@resend.dev",
                "to": "%s",
                "subject": "%s",
                "html": "%s"
            }
            """, to, subject, htmlContent.replace("\"", "\\\""));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RESEND_URL))
                .header("Authorization", "Bearer " + resendApiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("Email enviado correctamente");
            return true;
        } else {
            System.err.println("Error enviando email: " + response.statusCode() + " - " + response.body());
            return false;
        }
    }
}
