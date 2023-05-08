package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterUpdateDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserDeleteRequest;
import io.github.guilhermeabroncari.adopetapi.infra.security.AuthenticationFacade;
import io.github.guilhermeabroncari.adopetapi.rest.service.ShelterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelters")
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class ShelterController {

    private AuthenticationFacade authenticationFacade;
    private ShelterService shelterService;

    @GetMapping
    public Page<ShelterResponseDTO> page(@PageableDefault(size = 9) Pageable pageable) {
        return shelterService.page(pageable);
    }

    @Secured("ROLE_SHELTER")
    @PutMapping
    public ShelterResponseDTO update(@RequestBody ShelterUpdateDTO dto) {
        String email = getEmail();
        return shelterService.update(email, dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody @Valid UserDeleteRequest deleteRequest) {
        String email = getEmail();
        shelterService.delete(email, deleteRequest);
    }

    private String getEmail() {
        return authenticationFacade.getAuthentication().getName();
    }
}
