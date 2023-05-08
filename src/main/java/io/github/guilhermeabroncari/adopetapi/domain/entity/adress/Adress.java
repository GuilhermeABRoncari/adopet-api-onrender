package io.github.guilhermeabroncari.adopetapi.domain.entity.adress;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterUpdateDTO;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Adress {
    private Integer cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String number;

    public Adress(AdressRequestDTO adress) {
        this.cep = adress.cep();
        this.state = adress.state();
        this.city = adress.city();
        this.neighborhood = adress.neighborhood();
        this.street = adress.street();
        this.number = adress.number();
    }

    public void update(ShelterUpdateDTO dto) {
        if (dto.adress().cep() != null) this.cep = dto.adress().cep();
        if (dto.adress().state() != null) this.state = dto.adress().state();
        if (dto.adress().city() != null) this.city = dto.adress().city();
        if (dto.adress().neighborhood() != null) this.neighborhood = dto.adress().neighborhood();
        if (dto.adress().street() != null) this.street = dto.adress().street();
        if (dto.adress().number() != null) this.number = dto.adress().number();
    }
}
