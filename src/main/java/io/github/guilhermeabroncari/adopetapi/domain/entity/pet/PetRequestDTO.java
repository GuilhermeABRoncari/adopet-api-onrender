package io.github.guilhermeabroncari.adopetapi.domain.entity.pet;

import jakarta.validation.constraints.NotBlank;

public record PetRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String yearsOld,
        @NotBlank
        String animalSize,
        @NotBlank
        String description,
        @NotBlank
        String petProfileImage
) {
}
