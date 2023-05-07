package io.github.guilhermeabroncari.adopetapi.domain.entity.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
