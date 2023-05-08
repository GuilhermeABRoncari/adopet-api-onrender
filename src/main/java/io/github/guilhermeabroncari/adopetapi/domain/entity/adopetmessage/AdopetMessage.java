package io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage;

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

@Entity(name = "AdopetMessage")
@Table(name = "adopet_messages")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdopetMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @Column(name = "date_time")
    private OffsetDateTime dateTime;
    @ManyToOne
    private Tutor tutor;
    @ManyToOne
    private Shelter shelter;
    @ManyToOne
    private Pet pet;

    public void update(AdopetMessageUpdateDTO dto) {
        if (dto.message() != null) {
            this.message = dto.message();
            this.dateTime = OffsetDateTime.now();
        }
    }
}
