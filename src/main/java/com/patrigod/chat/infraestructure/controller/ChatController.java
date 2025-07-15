package com.patrigod.chat.infraestructure.controller;

import java.util.*;

import com.patrigod.chat.application.ChatOllamaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * REST controller to interact with the Ollama AI model.
 * Allows sending prompts and receiving responses related to
 * the World Heritage Cities in Spain.
 */
@RestController
@RequestMapping("/api/ollama")
@RequiredArgsConstructor
public class ChatController {

    private final ChatOllamaUseCase chatOllamaUseCase;

    @PostMapping("/chat")
    public ResponseEntity<String> chatWithOllama(@RequestBody Map<String, String> body) {
        String response = chatOllamaUseCase.chatOllama(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
