package com.patrigod.exception.messages;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorMessages {

    // entity not found
    public static final String CITY_NOT_FOUND_BY_ID_EXCEPTION = "City not found with this id: ";

    public static final String USER_NOT_FOUND_BY_ID_EXCEPTION = "User not found with this id: ";

    public static final String MONUMENT_NOT_FOUND_BY_ID_EXCEPTION = "Monument not found with this id: ";

    public static final String FOOD_NOT_FOUND_BY_ID_EXCEPTION = "Food not found with this id: ";

    public static final String EVENT_NOT_FOUND_BY_ID_EXCEPTION = "Event not found with this id: ";


    // email
    public static final String SEND_EMAIL_ERROR = "Email not sent correctly!";

    // chat with ollama
    public static final String CALLING_OLLAMA_ERROR = "Error calling Ollama: ";

    public static final String PROMPT_MISSING_ERROR = "The prompt is missing from the request.";

    // user
    public static final String USER_NOT_FOUND_ERROR = "User not found!";

    public static final String CHANGE_ROLE_USER_ERROR = "User to change role is not accepted!";

    public static final String CHANGE_STATE_ADMIN_ERROR = "The super administrator cannot be deactivated.";

    public static final String PASSWORD_ERROR = "Password error";


    public static final String CITY_NOT_EQUALS = "City is not equal than this article!";

}
