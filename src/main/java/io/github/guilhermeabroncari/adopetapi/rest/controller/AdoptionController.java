package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adoption.AdoptionRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adoption.AdoptionResponseDTO;
import io.github.guilhermeabroncari.adopetapi.infra.security.AuthenticationFacade;
import io.github.guilhermeabroncari.adopetapi.rest.service.AdoptionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adoptions")
@EnableMethodSecurity(securedEnabled = true)
@Secured("ROLE_SHELTER")
@AllArgsConstructor
public class AdoptionController {

    private AuthenticationFacade authenticationFacade;
    private AdoptionService adoptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdoptionResponseDTO adopt(@RequestBody @Valid AdoptionRequestDTO dto) {
        return adoptionService.adopt(getEmail(), dto);
    }

    @GetMapping
    public Page<AdoptionResponseDTO> getPage(Pageable pageable) {
        return adoptionService.getPage(getEmail(), pageable);
    }

    private String getEmail() {
        return authenticationFacade.getAuthentication().getName();
    }
}
