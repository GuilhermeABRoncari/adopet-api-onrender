package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserRole;
import io.github.guilhermeabroncari.adopetapi.rest.service.ShelterService;
import io.github.guilhermeabroncari.adopetapi.rest.service.SignService;
import io.github.guilhermeabroncari.adopetapi.rest.service.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private TutorService tutorService;
    private ShelterService shelterService;
    private SignService signService;

    @PostMapping("/sign")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO sign(@RequestBody @Valid UserRequestDTO dto) {
        signService.passwordValidation(dto.password(), dto.confirmationPassword());
        if (dto.userRole().equals(UserRole.TUTOR)) return tutorService.sign(dto);
        if (dto.userRole().equals(UserRole.SHELTER)) return shelterService.sign(dto);
        else throw new IllegalArgumentException();
    }
}
