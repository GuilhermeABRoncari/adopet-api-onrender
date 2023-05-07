package io.github.guilhermeabroncari.adopetapi.domain.entity.user;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.AdressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull
        UserRole userRole,
        @NotBlank
        String userName,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String password,
        @NotBlank
        String confirmationPassword,
        @NotBlank
        String about,
        @NotBlank
        String profileImage,
        @Valid
        AdressRequestDTO adress
) {
}
