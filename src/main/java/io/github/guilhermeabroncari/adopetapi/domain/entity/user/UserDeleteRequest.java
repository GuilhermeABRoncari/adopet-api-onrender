package io.github.guilhermeabroncari.adopetapi.domain.entity.user;

import jakarta.validation.constraints.NotBlank;

public record UserDeleteRequest(@NotBlank String password, @NotBlank String confirmationPassword) {
}
