package io.github.guilhermeabroncari.adopetapi.domain.entity.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Pet")
@Table(name = "pets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "years_old")
    private String yearsOld;
    @Column(name = "animal_size")
    private String animalSize;
    private String description;
    private Boolean adopted;
    @Column(name = "image")
    private String petProfileImage;
    @ManyToOne
    private Shelter shelter;

    public Pet(PetRequestDTO dto, Shelter shelter) {
        this.name = dto.name();
        this.yearsOld = dto.yearsOld();
        this.animalSize = dto.animalSize();
        this.description = dto.description();
        this.adopted = false;
        this.petProfileImage = dto.petProfileImage();
        this.shelter = shelter;
    }

    public void update(PetUpdateDTO petUpdateDTO) {
        if(petUpdateDTO.name() != null) this.name = petUpdateDTO.name();
        if(petUpdateDTO.yearsOld() != null) this.yearsOld = petUpdateDTO.yearsOld();
        if(petUpdateDTO.animalSize() != null) this.animalSize = petUpdateDTO.animalSize();
        if(petUpdateDTO.description() != null) this.description = petUpdateDTO.description();
        if(petUpdateDTO.petProfileImage() != null) this.petProfileImage = petUpdateDTO.petProfileImage();
    }
}
