package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.*;
import io.github.guilhermeabroncari.adopetapi.infra.security.TokenService;
import io.github.guilhermeabroncari.adopetapi.rest.service.ShelterService;
import io.github.guilhermeabroncari.adopetapi.rest.service.SignService;
import io.github.guilhermeabroncari.adopetapi.rest.service.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TutorService tutorService;
    private ShelterService shelterService;
    private SignService signService;
    private TokenService tokenService;

    @PostMapping("/sign")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO sign(@RequestBody @Valid UserRequestDTO dto) {
        signService.passwordValidation(dto.password(), dto.confirmationPassword());
        if (dto.userRole().equals(UserRole.TUTOR)) return tutorService.sign(dto);
        if (dto.userRole().equals(UserRole.SHELTER)) return shelterService.sign(dto);
        else throw new IllegalArgumentException();
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid AuthRequestDTO authenticationDTO) {
        if (tutorService.exists(authenticationDTO.email())) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var tutor = (Tutor) authentication.getPrincipal();
            var tokenJWT = tokenService.generateToken(tutor);

            return new AuthResponseDTO(tutor, tokenJWT);
        } else if (shelterService.exists(authenticationDTO.email())) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var shelter = (Shelter) authentication.getPrincipal();
            var tokenJWT = tokenService.generateToken(shelter);

            return new AuthResponseDTO(shelter, tokenJWT);
        } else throw new IllegalArgumentException("Invalid email or password.");
    }
}
