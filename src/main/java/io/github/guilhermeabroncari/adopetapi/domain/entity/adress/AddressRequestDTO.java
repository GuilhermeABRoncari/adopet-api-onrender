package io.github.guilhermeabroncari.adopetapi.domain.entity.adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequestDTO(
        @NotNull
        Integer cep,
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        String neighborhood,
        @NotBlank
        String street,
        @NotBlank
        String number
) {
}
