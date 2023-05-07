package io.github.guilhermeabroncari.adopetapi.domain.entity.shelter;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.AdressResponseDTO;

public record ShelterResponseDTO(Long shelterId, String shelterName, String phone, String email, AdressResponseDTO adress) {
    public ShelterResponseDTO(Shelter shelter) {
        this(shelter.getId(), shelter.getShelterName(), shelter.getPhone(), shelter.getEmail(), new AdressResponseDTO(shelter.getAdress()));
    }
}
