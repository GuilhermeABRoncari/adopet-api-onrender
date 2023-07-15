package io.github.guilhermeabroncari.adopetapi.domain.entity.tutor;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.AddressResponseDTO;

public record TutorResponseDTO(Long tutorId, String tutorName, String phone, String email, AddressResponseDTO adress) {
    public TutorResponseDTO(Tutor tutor) {
        this(tutor.getId(), tutor.getTutorName(), tutor.getPhone(), tutor.getEmail(), new AddressResponseDTO(tutor.getAddress()));
    }
}
