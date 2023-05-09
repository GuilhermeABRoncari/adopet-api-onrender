package io.github.guilhermeabroncari.adopetapi.domain.entity.adoption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity(name = "Adoption")
@Table(name = "adoptions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazeInitializer"})
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Shelter shelter;
    @ManyToOne
    private Tutor tutor;
    @OneToOne
    private Pet pet;
    private String comment;
    @Column(name = "date_time")
    private OffsetDateTime dateTime;
}
