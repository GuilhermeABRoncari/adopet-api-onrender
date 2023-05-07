package io.github.guilhermeabroncari.adopetapi.domain.entity.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    UserDetails findByEmail(String username);

    boolean existsByEmail(String email);
}
