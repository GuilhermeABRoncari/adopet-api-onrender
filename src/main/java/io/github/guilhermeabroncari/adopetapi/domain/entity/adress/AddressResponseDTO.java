package io.github.guilhermeabroncari.adopetapi.domain.entity.adress;

public record AddressResponseDTO(Integer cep, String state, String city, String neighborhood, String street,
                                 String number) {
    public AddressResponseDTO(Address address) {
        this(address.getCep(), address.getState(), address.getCity(), address.getNeighborhood(), address.getStreet(),
                address.getNumber());
    }
}
