package io.github.guilhermeabroncari.adopetapi.domain.entity.pet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}