package io.github.guilhermeabroncari.adopetapi.domain.entity.adress;

public record AdressResponseDTO(Integer cep, String state, String city, String neighborhood, String street,
                                String number) {
    public AdressResponseDTO(Adress adress) {
        this(adress.getCep(), adress.getState(), adress.getCity(), adress.getNeighborhood(), adress.getStreet(),
                adress.getNumber());
    }
}
