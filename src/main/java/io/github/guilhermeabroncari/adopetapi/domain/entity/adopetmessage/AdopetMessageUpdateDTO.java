package io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage;

import jakarta.validation.constraints.NotNull;

public record AdopetMessageUpdateDTO(
        @NotNull
        Long messageId,
        String message
) {
}
