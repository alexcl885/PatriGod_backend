package com.patrigod.shared.exception.messages;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorMessages {

    public static final String CITY_NOT_FOUND = "City not found with this id: ";

    public static final String SEND_EMAIL_ERROR = "Email not sent correctly!";

    public static final String CALLING_OLLAMA_ERROR = "Error calling Ollama: ";

    public static final String PROMPT_MISSING_ERROR = "The prompt is missing from the request.";

}
