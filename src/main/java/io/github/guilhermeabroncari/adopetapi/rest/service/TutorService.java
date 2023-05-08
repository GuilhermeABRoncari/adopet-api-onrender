package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.Adress;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorUpdateDTO;
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
public class TutorService {

    private TutorRepository tutorRepository;
    private SecurityConfigurations securityConfigurations;
    private SignService signService;
    private static final String INVALID_EMAIL = "This email is already in use.";

    @Transactional
    public UserResponseDTO sign(UserRequestDTO dto) {
        if (tutorRepository.existsByEmail(dto.email())) throw new IllegalArgumentException(INVALID_EMAIL);
        var tutor = new Tutor(null, dto.userName(), dto.email(), dto.phone(),
                securityConfigurations.passwordEncoder().encode(dto.password()), dto.about(), dto.profileImage(), new Adress(dto.adress()));
        tutorRepository.save(tutor);
        return new UserResponseDTO(tutor);
    }

    public boolean exists(String email) {
        return tutorRepository.existsByEmail(email);
    }

    @Transactional
    public TutorResponseDTO update(String email, TutorUpdateDTO dto) {
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        tutor.update(dto);
        return new TutorResponseDTO(tutor);
    }

    public Page<TutorResponseDTO> page(Pageable pageable) {
        return tutorRepository.findAll(pageable).map(TutorResponseDTO::new);
    }

    @Transactional
    public void delete(String email, UserDeleteRequest deleteRequest) {
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        signService.passwordValidation(deleteRequest.password(), deleteRequest.confirmationPassword());
        if (!securityConfigurations.passwordEncoder().matches(deleteRequest.password(), tutor.getPassword()))
            throw new IllegalArgumentException("Invalid password.");
        tutorRepository.delete(tutor);
    }
}
