package io.github.guilhermeabroncari.adopetapi.domain.entity.adoption;

import jakarta.validation.constraints.NotNull;

public record AdoptionRequestDTO(
        @NotNull
        Long petId,
        @NotNull
        Long tutorId,
        String comment
) {
}
