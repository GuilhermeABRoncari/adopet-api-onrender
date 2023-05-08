package io.github.guilhermeabroncari.adopetapi.domain.entity.shelter;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.AdressUpdateDTO;

public record ShelterUpdateDTO(String shelterName, String phone, String about, String profileImage,
                               AdressUpdateDTO adress) {
}
