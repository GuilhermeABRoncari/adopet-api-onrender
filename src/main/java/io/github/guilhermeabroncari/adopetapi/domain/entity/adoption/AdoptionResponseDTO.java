package io.github.guilhermeabroncari.adopetapi.domain.entity.adoption;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterResponseDTO;

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
    public AdoptionResponseDTO(Adoption adption) {
        this(adption.getId(), adption.getPet().getId(), adption.getPet().getName(),
                adption.getTutor().getId(), adption.getTutor().getTutorName(), new ShelterResponseDTO(adption.getShelter()),
                adption.getComment(), adption.getDateTime());
    }
}
