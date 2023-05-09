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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AdoptionService {

    private AdoptionRepository adoptionRepository;
    private TutorRepository tutorRepository;
    private ShelterRepository shelterRepository;
    private PetRepository petRepository;
    private static final String ENTITY_NOT_FOUND = "Entity id not found for: ";
    private static final String PET_NOT_AVALIABLE = "This pet is not available to adoption.";
    private static final String NO_ADOPTION_YET = "No adoption has happened yet.";

    @Transactional
    public AdoptionResponseDTO adopt(String email, AdoptionRequestDTO dto) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        var tutor = tutorRepository.findById(dto.tutorId()).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "tutor"));
        var pet = petRepository.findById(dto.petId()).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "pet"));

        if (!shelter.getPetList().contains(pet)) throw new IllegalArgumentException(ENTITY_NOT_FOUND + "pet");

        if (pet.getAdopted()) throw new IllegalArgumentException(PET_NOT_AVALIABLE);
        pet.adoption();

        var adption = new Adoption(null, shelter, tutor, pet, dto.comment(), OffsetDateTime.now());
        adoptionRepository.save(adption);
        shelter.getAdoptions().add(adption);
        return new AdoptionResponseDTO(adption);
    }

    public Page<AdoptionResponseDTO> getPage(String email, Pageable pageable) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        if (shelter.getAdoptions().isEmpty()) throw new IllegalArgumentException(NO_ADOPTION_YET);
        List<Adoption> adoptionList = shelter.getAdoptions();

        adoptionList.sort(Comparator.comparing(Adoption::getDateTime).reversed());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<AdoptionResponseDTO> adoptionResponseList = adoptionList.stream()
                .skip(startItem)
                .limit(pageSize)
                .map(AdoptionResponseDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(adoptionResponseList, PageRequest.of(currentPage, pageSize), adoptionList.size());
    }
}
