package com.patrigod.chat.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controlador REST para interactuar con el modelo de IA Ollama.
 * Permite enviar prompts y recibir respuestas relacionadas con las Ciudades
 * Patrimonio de la Humanidad en España.
 */
@RestController
@RequestMapping("/api/ollama")
public class ChatController {

    private static final String OLLAMA_URL = "http://localhost:11434/api/chat";
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    /**
     * Constructor que inyecta el ObjectMapper y crea el RestTemplate.
     * 
     * @param objectMapper el mapeador de JSON de Jackson
     */
    public ChatController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Endpoint para enviar un mensaje al modelo Ollama y obtener una respuesta.
     * 
     * Este método recibe un JSON con el campo "prompt" (mensaje del usuario) y opcionalmente "model" (nombre del modelo a usar).
     * 
     * 1. Valida que el prompt no esté vacío.
     * 2. Construye un mensaje de sistema (systemPrompt) que limita las respuestas del modelo a temas de las Ciudades Patrimonio de la Humanidad en España.
     * 3. Prepara la estructura de mensajes para Ollama:
     *    - El primer mensaje es de tipo "system" con el prompt de sistema.
     *    - El segundo mensaje es de tipo "user" con el prompt del usuario.
     * 4. Crea un objeto JSON con el modelo, el flag "stream" (desactivado) y la lista de mensajes.
     * 5. Usa ObjectMapper para convertir el objeto Java a un String JSON.
     * 6. Prepara la cabecera HTTP para indicar que el contenido es JSON.
     * 7. Envía la petición POST a Ollama usando RestTemplate.
     * 8. Si la respuesta es exitosa, devuelve el cuerpo de la respuesta de Ollama.
     * 9. Si hay error, devuelve el mensaje de error correspondiente.
     * 
     * @param body Mapa con los campos "prompt" (obligatorio) y "model" (opcional)
     * @return Respuesta generada por el modelo Ollama o mensaje de error
     */
    @PostMapping("/chat")
    public ResponseEntity<String> chatWithOllama(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        String model = body.getOrDefault("model", "llama3.2:3b");
        if (prompt == null || prompt.isBlank()) {
            return ResponseEntity.badRequest().body("Falta el prompt en la solicitud.");
        }
        String systemPrompt = "Eres un asistente experto en las Ciudades Patrimonio de la Humanidad en España. " +
                "Solo puedes responder sobre temas relacionados con estas ciudades. Si la pregunta no tiene relación, " +
                "indica amablemente que solo puedes responder sobre las ciudades patrimonio de la humanidad en España. " +
                "Si necesitas ayuda, estoy aquí para ello.";

        try {
            //construcción del cuerpo JSON para Ollama
            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("model", model);
            jsonBody.put("stream", false);

            List<Map<String, String>> messages = new ArrayList<>();
            //mensaje de sistema
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);
            //mensaje del usuario
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);

            jsonBody.put("messages", messages);

            //serializo el objeto Java a JSON
            String json = objectMapper.writeValueAsString(jsonBody);

            //preparo la cabecera HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            //creo la petición HTTP
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            //envio la petición a Ollama y obtendra una respuesta
            ResponseEntity<String> response = restTemplate.postForEntity(OLLAMA_URL, 
                                                                        request, 
                                                                        String.class);

            //devuelve la respuesta de Ollama si es exitosa
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
