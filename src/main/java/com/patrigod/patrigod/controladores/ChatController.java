package com.patrigod.patrigod.controladores;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controlador REST para interactuar con el modelo de IA Ollama.
 * Permite enviar prompts y recibir respuestas relacionadas con las Ciudades Patrimonio de la Humanidad en España.
 */
@RestController
@RequestMapping("/api/ollama")
public class ChatController {

    private static final String OLLAMA_URL = "http://localhost:11434/api/chat";
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    /**
     * Constructor que inyecta el ObjectMapper y crea el RestTemplate.
     * @param objectMapper el mapeador de JSON de Jackson
     */
    public ChatController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Endpoint para enviar un mensaje al modelo Ollama y obtener una respuesta.
     * El mensaje de sistema fuerza al modelo a responder solo sobre ciudades patrimonio de la humanidad en España.
     * 
     * @param body Mapa con los campos "prompt" (obligatorio) y "model" (opcional)
     * @return Respuesta generada por el modelo Ollama o mensaje de error
     */
    @PostMapping("/chat")
    public ResponseEntity<String> chatWithOllama(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        String model = body.getOrDefault("model", "llama3.2");
        if (prompt == null || prompt.isBlank()) {
            return ResponseEntity.badRequest().body("Falta el prompt en la solicitud.");
        }

        // Mensaje de sistema para guiar al modelo
        String systemPrompt = "Eres un asistente experto en las Ciudades Patrimonio de la Humanidad en España. " +
                "Solo puedes responder sobre temas relacionados con estas ciudades. Si la pregunta no tiene relación, " +
                "indica amablemente que solo puedes responder sobre las ciudades patrimonio de la humanidad en España. " +
                "Si necesitas ayuda, estoy aquí para ello.";

        try {
            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("model", model);
            jsonBody.put("stream", false);

            List<Map<String, String>> messages = new ArrayList<>();
            // Mensaje de sistema
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);
            // Mensaje del usuario
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);
            jsonBody.put("messages", messages);

            String json = objectMapper.writeValueAsString(jsonBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(OLLAMA_URL, request, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode())
                        .body("Error de Ollama: " + response.getBody());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al llamar a Ollama: " + e.getMessage());
        }
    }
}
