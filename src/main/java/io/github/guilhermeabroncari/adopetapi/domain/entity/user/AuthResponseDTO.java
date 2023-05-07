package io.github.guilhermeabroncari.adopetapi.domain.entity.user;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;

public record AuthResponseDTO(Long id, String userName, String token) {
    public AuthResponseDTO(Tutor tutor, String tokenJWT) {
        this(tutor.getId(), tutor.getTutorName(), tokenJWT);
    }

    public AuthResponseDTO(Shelter shelter, String tokenJWT) {
        this(shelter.getId(), shelter.getShelterName(), tokenJWT);
    }
}
