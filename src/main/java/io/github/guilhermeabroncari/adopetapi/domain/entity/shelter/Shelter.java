package io.github.guilhermeabroncari.adopetapi.domain.entity.shelter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.Adress;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Shelter")
@Table(name = "shelters")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shelterName;
    private String email;
    private String phone;
    private String password;
    private String about;
    private String shelterProfileImage;
    @Embedded
    private Adress adress;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Pet> petList = new ArrayList<>();
}
