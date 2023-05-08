package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessage;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class AdopetMessageService {

    private AdopetMessageRepository adopetMessageRepository;
    private TutorRepository tutorRepository;
    private ShelterRepository shelterRepository;
    private PetRepository petRepository;
    private static final String ENTITY_NOT_FOUND = "Entity id not found for: ";

    @Transactional
    public AdopetMessageResponseDTO sendMessage(String email, AdopetMessageRequestDTO dto) {
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        Shelter shelter = shelterRepository.findById(dto.shelterId()).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "shelter"));
        Pet pet = petRepository.findByNameLike("%" + dto.petName() + "%");
        if (pet == null) throw new IllegalArgumentException("Pet whit name like: " + dto.petName() + " not found.");

        if (!shelter.getPetList().contains(pet)) throw new IllegalArgumentException(ENTITY_NOT_FOUND + "pet in this shelter.");
        var message = new AdopetMessage(null, dto.message(), OffsetDateTime.now(), tutor, shelter, pet);
        adopetMessageRepository.save(message);
        tutor.getMessageList().add(message);
        shelter.getMessageList().add(message);

        return new AdopetMessageResponseDTO(message);
    }
}
