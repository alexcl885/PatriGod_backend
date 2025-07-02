package com.patrigod.email.application;

public interface SendUpdateEmailUseCase {
    boolean sendUpdateEmail(String to, String updateMessage);
}
