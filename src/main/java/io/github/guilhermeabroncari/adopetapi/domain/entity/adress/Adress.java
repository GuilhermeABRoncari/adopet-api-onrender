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

    public void update(AdressUpdateDTO adress) {
        if (adress.cep() != null) this.cep = adress.cep();
        if (adress.state() != null) this.state = adress.state();
        if (adress.city() != null) this.city = adress.city();
        if (adress.neighborhood() != null) this.neighborhood = adress.neighborhood();
        if (adress.street() != null) this.street = adress.street();
        if (adress.number() != null) this.number = adress.number();
    }
}
