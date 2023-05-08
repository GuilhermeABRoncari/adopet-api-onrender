package io.github.guilhermeabroncari.adopetapi.domain.entity.adress;

public record AdressUpdateDTO(Integer cep, String state, String city, String neighborhood, String street,
                              String number) {
}
