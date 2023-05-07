package io.github.guilhermeabroncari.adopetapi.domain.entity.pet;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterResponseDTO;

public record PetResponseDTO(Long id, String name, String yearsOld, String animalSize, String description, String petProfileImage, ShelterResponseDTO shelter) {
    public PetResponseDTO(Pet pet) {
        this(pet.getId(), pet.getName(), pet.getYearsOld(), pet.getAnimalSize(), pet.getDescription(), pet.getPetProfileImage(), new ShelterResponseDTO(pet.getShelter()));
    }

}
