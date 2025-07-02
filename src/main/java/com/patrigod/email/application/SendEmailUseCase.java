package com.patrigod.email.application;

public interface SendEmailUseCase {
    boolean sendEmail(String to, String subject, String htmlContent);
}
