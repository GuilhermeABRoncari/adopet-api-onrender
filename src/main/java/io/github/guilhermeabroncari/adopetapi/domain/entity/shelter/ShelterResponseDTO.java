package io.github.guilhermeabroncari.adopetapi.domain.entity.shelter;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.AddressResponseDTO;

public record ShelterResponseDTO(Long shelterId, String shelterName, String phone, String email, AddressResponseDTO adress) {
    public ShelterResponseDTO(Shelter shelter) {
        this(shelter.getId(), shelter.getShelterName(), shelter.getPhone(), shelter.getEmail(), new AddressResponseDTO(shelter.getAddress()));
    }
}
