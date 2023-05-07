package io.github.guilhermeabroncari.adopetapi.domain.entity.shelter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    UserDetails findByEmail(String username);

    boolean existsByEmail(String email);
}
