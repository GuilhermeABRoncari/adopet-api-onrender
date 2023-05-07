package io.github.guilhermeabroncari.adopetapi.domain.entity.pet;

import jakarta.validation.constraints.NotNull;

public record PetUpdateDTO(@NotNull Long petId, String name, String yearsOld, String animalSize, String description, String petProfileImage) {
}
