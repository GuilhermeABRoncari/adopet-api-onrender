package io.github.guilhermeabroncari.adopetapi.domain.entity.adoptaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
