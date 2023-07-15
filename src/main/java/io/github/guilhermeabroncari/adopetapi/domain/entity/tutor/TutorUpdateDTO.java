package io.github.guilhermeabroncari.adopetapi.domain.entity.tutor;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.AddressUpdateDTO;

public record TutorUpdateDTO(String tutorName, String phone, String about, String profileImage,
                             AddressUpdateDTO adress) {
}
