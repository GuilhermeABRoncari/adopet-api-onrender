package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.Adress;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserResponseDTO;
import io.github.guilhermeabroncari.adopetapi.infra.security.SecurityConfigurations;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TutorService {

    private TutorRepository tutorRepository;
    private SecurityConfigurations securityConfigurations;
    private static final String INVALID_EMAIL = "This email is already in use.";

    @Transactional
    public UserResponseDTO sign(UserRequestDTO dto) {
        if (tutorRepository.existsByEmail(dto.email())) throw new IllegalArgumentException(INVALID_EMAIL);
        var tutor = new Tutor(null, dto.userName(), dto.email(), dto.phone(),
                securityConfigurations.passwordEncoder().encode(dto.password()), dto.about(), dto.profileImage(), new Adress(dto.adress()));
        tutorRepository.save(tutor);
        return new UserResponseDTO(tutor);
    }
}
