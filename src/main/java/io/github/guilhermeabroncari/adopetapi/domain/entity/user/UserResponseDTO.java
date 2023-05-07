package io.github.guilhermeabroncari.adopetapi.domain.entity.user;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;

public record UserResponseDTO(Long id, String userName) {
    public UserResponseDTO(Shelter shelter) {
        this(shelter.getId(), shelter.getUsername());
    }
    public UserResponseDTO(Tutor tutor) {
        this(tutor.getId(), tutor.getUsername());
    }
}
