package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.*;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.exception.DomainNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetService {

    private PetRepository petRepository;
    private ShelterRepository shelterRepository;
    private static final String PET_NOT_FOUND = "Pet not found.";

    @Transactional
    public PetResponseDTO create(String email, PetRequestDTO dto) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        var pet = new Pet(dto, shelter);
        petRepository.save(pet);
        shelter.getPetList().add(pet);
        return new PetResponseDTO(pet);
    }

    public Page<PetResponseDTO> page(String email, Pageable pageable) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        List<Pet> petList = shelter.getPetList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), petList.size());
        List<Pet> sublist = petList.subList(start, end);

        List<PetResponseDTO> petResponseList = sublist.stream().map(PetResponseDTO::new).collect(Collectors.toList());

        return new PageImpl<>(petResponseList, pageable, petList.size());
    }

    public PetResponseDTO find(String email, Long id) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        var pet = petRepository.findById(id).orElseThrow(() -> new DomainNotFoundException(PET_NOT_FOUND));
        if (!shelter.getPetList().contains(pet)) throw new DomainNotFoundException(PET_NOT_FOUND);
        return new PetResponseDTO(pet);
    }

    @Transactional
    public PetResponseDTO update(String email, PetUpdateDTO petUpdateDTO) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        var pet = petRepository.findById(petUpdateDTO.petId()).orElseThrow(() -> new DomainNotFoundException(PET_NOT_FOUND));
        if (!shelter.getPetList().contains(pet)) throw new DomainNotFoundException(PET_NOT_FOUND);
        pet.update(petUpdateDTO);
        petRepository.save(pet);
        return new PetResponseDTO(pet);
    }

    @Transactional
    public void delete(String email, Long id) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        var pet = petRepository.findById(id).orElseThrow(() -> new DomainNotFoundException(PET_NOT_FOUND));
        if (!shelter.getPetList().contains(pet)) throw new DomainNotFoundException(PET_NOT_FOUND);
        if(!petRepository.existsById(id)) throw new DomainNotFoundException(PET_NOT_FOUND);
        petRepository.deleteById(id);
    }
}
