package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.Adress;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterUpdateDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserDeleteRequest;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserResponseDTO;
import io.github.guilhermeabroncari.adopetapi.infra.security.SecurityConfigurations;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShelterService {
    private ShelterRepository shelterRepository;
    private SecurityConfigurations securityConfigurations;
    private SignService signService;
    private static final String INVALID_EMAIL = "This email is already in use.";
    private static final String TAKE_CARE_OF = "They cannot exclude shelter because they have pets to take care of.";

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

    @Transactional
    public ShelterResponseDTO update(String email, ShelterUpdateDTO dto) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        shelter.update(dto);
        return new ShelterResponseDTO(shelter);
    }

    public Page<ShelterResponseDTO> page(Pageable pageable) {
        return shelterRepository.findAll(pageable).map(ShelterResponseDTO::new);
    }

    @Transactional
    public void delete(String email, UserDeleteRequest deleteRequest) {
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        signService.passwordValidation(deleteRequest.password(), deleteRequest.confirmationPassword());
        if (!securityConfigurations.passwordEncoder().matches(deleteRequest.password(), shelter.getPassword())) throw new IllegalArgumentException("Invalid password.");
        if (!shelter.getPetList().isEmpty()) throw new IllegalArgumentException(TAKE_CARE_OF);
            shelterRepository.delete(shelter);
    }
}
