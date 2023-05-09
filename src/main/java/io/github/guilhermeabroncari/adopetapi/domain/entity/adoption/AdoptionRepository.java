package io.github.guilhermeabroncari.adopetapi.domain.entity.adoption;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
