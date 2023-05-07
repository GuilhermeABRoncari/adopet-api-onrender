package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.*;
import io.github.guilhermeabroncari.adopetapi.domain.exception.DomainNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private PetRepository petRepository;
    private static final String PET_NOT_FOUND = "Pet not found.";

    @Transactional
    public PetResponseDTO create(PetRequestDTO dto) {
        var pet = new Pet(dto);
        petRepository.save(pet);
        return new PetResponseDTO(pet);
    }

    public Page<PetResponseDTO> page(Pageable pageable) {
        return petRepository.findAllByAdoptedFalse(pageable).map(PetResponseDTO::new);
    }

    public PetResponseDTO find(Long id) {
        return petRepository.findById(id).map(PetResponseDTO::new).orElseThrow(() -> new DomainNotFoundException(PET_NOT_FOUND));
    }

    @Transactional
    public PetResponseDTO update(PetUpdateDTO petUpdateDTO) {
        var pet = petRepository.findById(petUpdateDTO.petId()).orElseThrow(() -> new DomainNotFoundException(PET_NOT_FOUND));
        pet.update(petUpdateDTO);
        petRepository.save(pet);
        return new PetResponseDTO(pet);
    }

    @Transactional
    public void delete(Long id) {
        if(!petRepository.existsById(id)) throw new DomainNotFoundException(PET_NOT_FOUND);
        petRepository.deleteById(id);
    }
}
