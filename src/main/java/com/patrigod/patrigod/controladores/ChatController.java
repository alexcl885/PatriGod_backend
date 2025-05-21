package com.patrigod.patrigod.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/ollama")
public class ChatController {

    private final String OLLAMA_URL = "http://localhost:11434/api/chat";

    
    private ObjectMapper objectMapper;


    public ChatController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chatWithOllama(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");

    if (prompt == null || prompt.isEmpty()) {
        return ResponseEntity.badRequest().body("Falta el prompt en la solicitud.");
    }

    try {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("model", "mistral");
        jsonBody.put("stream", false);

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
        messages.add(message);
        RestTemplate restTemplate = new RestTemplate();
        jsonBody.put("messages", messages);

        String json = objectMapper.writeValueAsString(jsonBody);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(OLLAMA_URL, request, String.class);
        return ResponseEntity.ok(response.getBody());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al llamar a Ollama: " + e.getMessage());
    }
}
}
