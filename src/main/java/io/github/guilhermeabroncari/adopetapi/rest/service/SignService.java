package io.github.guilhermeabroncari.adopetapi.rest.service;

import org.springframework.stereotype.Service;

@Service
public class SignService {

    private static final String INVALID_PASSWORD = "The password must contain at least one uppercase letter, one number, and be between 6 and 15 characters.";

    public void passwordValidation(String password, String confirmationPassword) {
        if(!password.equals(confirmationPassword)) {
            throw new IllegalArgumentException("The entered passwords must be the same.");
        }

        if (password.length() < 6 || password.length() > 15) {
            throw new IllegalArgumentException(INVALID_PASSWORD);
        }

        boolean hasUppercase = false;
        boolean hasNumber = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            }
        }

        if (!hasUppercase || !hasNumber) {
            throw new IllegalArgumentException(INVALID_PASSWORD);
        }
    }
}
