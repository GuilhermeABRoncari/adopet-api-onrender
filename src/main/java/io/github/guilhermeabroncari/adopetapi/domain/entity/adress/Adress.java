package io.github.guilhermeabroncari.adopetapi.domain.entity.adress;

import jakarta.persistence.Embeddable;

@Embeddable
public class Adress {
    private Integer cpf;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
}
