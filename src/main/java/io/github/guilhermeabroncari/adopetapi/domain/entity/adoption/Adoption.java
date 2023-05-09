package io.github.guilhermeabroncari.adopetapi.domain.entity.adoption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
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
    @Column(name = "date_time")
    private OffsetDateTime dateTime;
    @OneToOne
    private Pet pet;
    private String comment;
}
