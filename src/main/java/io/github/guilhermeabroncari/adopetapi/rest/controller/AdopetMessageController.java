package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageResponseDTO;
import io.github.guilhermeabroncari.adopetapi.infra.security.AuthenticationFacade;
import io.github.guilhermeabroncari.adopetapi.rest.service.AdopetMessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class AdopetMessageController {
    private AuthenticationFacade authenticationFacade;
    private AdopetMessageService adopetMessageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_TUTOR")
    public AdopetMessageResponseDTO sendMessage(@RequestBody @Valid AdopetMessageRequestDTO dto) {
        String email = getEmail();
        return adopetMessageService.sendMessage(email, dto);
    }

    private String getEmail() {
        return authenticationFacade.getAuthentication().getName();
    }
}
