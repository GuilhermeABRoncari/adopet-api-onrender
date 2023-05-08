package io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorResponseDTO;

import java.time.OffsetDateTime;

public record AdopetMessageResponseDTO(Long messageId, TutorResponseDTO tutor, PetResponseDTO pet,
                                       String message, OffsetDateTime dateTime) {
    public AdopetMessageResponseDTO(AdopetMessage message) {
        this(message.getId(), new TutorResponseDTO(message.getTutor()), new PetResponseDTO(message.getPet()),
                message.getMessage(), message.getDateTime());
    }
}
