package io.github.guilhermeabroncari.adopetapi.domain.entity.pet;

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
    @Column(name = "shelter_id")
    private Long shelter;

    public Pet(PetRequestDTO dto) {
        this.name = dto.name();
        this.yearsOld = dto.yearsOld();
        this.animalSize = dto.animalSize();
        this.description = dto.description();
        this.adopted = false;
        this.petProfileImage = dto.petProfileImage();
    }
}
