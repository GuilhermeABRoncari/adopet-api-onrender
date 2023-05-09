package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adoption.Adoption;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adoption.AdoptionRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adoption.AdoptionRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adoption.AdoptionResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class AdoptionService {

    private AdoptionRepository adoptionRepository;
    private TutorRepository tutorRepository;
    private ShelterRepository shelterRepository;
    private PetRepository petRepository;
    private static final String ENTITY_NOT_FOUND = "Entity id not found for: ";
    private static final String PET_NOT_AVALIABLE = "This pet is not available to adoption.";

    @Transactional
    public AdoptionResponseDTO adopt(String email, AdoptionRequestDTO dto) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        var tutor = tutorRepository.findById(dto.tutorId()).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "tutor"));
        var pet = petRepository.findById(dto.petId()).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "pet"));

        if (!shelter.getPetList().contains(pet)) throw new IllegalArgumentException(ENTITY_NOT_FOUND + "pet");

        if (pet.getAdopted()) throw new IllegalArgumentException("This pet is not available to adoption.");
        pet.adoption();

        var adption = new Adoption(null, OffsetDateTime.now(), pet, dto.comment());
        adoptionRepository.save(adption);
        return new AdoptionResponseDTO(adption, tutor);
    }
}
