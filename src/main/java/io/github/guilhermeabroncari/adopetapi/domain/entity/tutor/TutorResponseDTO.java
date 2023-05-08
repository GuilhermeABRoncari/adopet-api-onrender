package io.github.guilhermeabroncari.adopetapi.domain.entity.tutor;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.AdressResponseDTO;

public record TutorResponseDTO(Long tutorId, String tutorName, String phone, String email, AdressResponseDTO adress) {
    public TutorResponseDTO(Tutor tutor) {
        this(tutor.getId(), tutor.getTutorName(), tutor.getPhone(), tutor.getEmail(), new AdressResponseDTO(tutor.getAdress()));
    }
}
