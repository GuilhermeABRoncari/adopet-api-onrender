package io.github.guilhermeabroncari.adopetapi.domain.entity.pet;

public record PetResponseDTO(Long id, String name, String yearsOld, String animalSize, String description, String petProfileImage) {
    public PetResponseDTO(Pet pet) {
        this(pet.getId(), pet.getName(), pet.getYearsOld(), pet.getAnimalSize(), pet.getDescription(), pet.getPetProfileImage());
    }
}
