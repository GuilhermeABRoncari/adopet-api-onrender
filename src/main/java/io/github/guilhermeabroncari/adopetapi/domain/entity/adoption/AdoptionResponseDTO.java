package io.github.guilhermeabroncari.adopetapi.domain.entity.adoption;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;

import java.time.OffsetDateTime;

public record AdoptionResponseDTO(
        Long adoptionId,
        Long petId,
        String petName,
        Long tutorId,
        String tutorName,
        ShelterResponseDTO shelter,
        String comment,
        OffsetDateTime dateTime
) {
    public AdoptionResponseDTO(Adoption adption, Tutor tutor) {
        this(adption.getId(), adption.getPet().getId(), adption.getPet().getName(),
                tutor.getId(), tutor.getTutorName(), new ShelterResponseDTO(adption.getPet().getShelter()),
                adption.getComment(), adption.getDateTime());
    }
}
