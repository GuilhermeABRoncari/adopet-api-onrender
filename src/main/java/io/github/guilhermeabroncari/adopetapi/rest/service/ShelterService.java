package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.Adress;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserResponseDTO;
import io.github.guilhermeabroncari.adopetapi.infra.security.SecurityConfigurations;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShelterService {
    private ShelterRepository shelterRepository;
    private SecurityConfigurations securityConfigurations;
    private static final String INVALID_EMAIL = "This email is already in use.";

    @Transactional
    public UserResponseDTO sign(UserRequestDTO dto) {
        if (shelterRepository.existsByEmail(dto.email())) throw new IllegalArgumentException(INVALID_EMAIL);
        var shelter = new Shelter(null, dto.userName(), dto.email(), dto.phone(),
                securityConfigurations.passwordEncoder().encode(dto.password()),
                dto.about(), dto.profileImage(), new Adress(dto.adress()), null);
        shelterRepository.save(shelter);
        return new UserResponseDTO(shelter);
    }

    public boolean exists(String email) {
        return shelterRepository.existsByEmail(email);
    }
}
