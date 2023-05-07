package io.github.guilhermeabroncari.adopetapi.domain.entity.adress;

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
}
