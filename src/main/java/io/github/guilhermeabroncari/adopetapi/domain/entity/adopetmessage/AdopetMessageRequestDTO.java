package io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdopetMessageRequestDTO(
        @NotNull
        Long shelterId,
        @NotBlank
        String petName,
        @NotBlank
        String message) {
}
