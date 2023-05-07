package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetResponseDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private PetRepository petRepository;

    @Transactional
    public PetResponseDTO create(PetRequestDTO dto) {
        var pet = new Pet(dto);
        petRepository.save(pet);
        return new PetResponseDTO(pet);
    }

    public List<Pet> page() {
        return petRepository.findAll();
    }
}
