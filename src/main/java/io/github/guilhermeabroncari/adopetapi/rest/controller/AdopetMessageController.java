package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.AdopetMessageUpdateDTO;
import io.github.guilhermeabroncari.adopetapi.infra.security.AuthenticationFacade;
import io.github.guilhermeabroncari.adopetapi.rest.service.AdopetMessageService;
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

    @GetMapping
    public Page<AdopetMessageResponseDTO> pageMessages(@PageableDefault(size = 9, sort = "date_time,desc") Pageable pageable) {
        String email = getEmail();
        return adopetMessageService.pageMessages(email, pageable);
    }
    @GetMapping("{id}")
    public AdopetMessageResponseDTO getMessageById(@PathVariable Long id) {
        String email = getEmail();
        return adopetMessageService.getMessageById(email, id);
    }

    @PutMapping
    public AdopetMessageResponseDTO update(@RequestBody @Valid AdopetMessageUpdateDTO dto) {
        String email = getEmail();
        return adopetMessageService.updateMessage(email, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        String email = getEmail();
        adopetMessageService.deleteMessage(email, id);
    }

    private String getEmail() {
        return authenticationFacade.getAuthentication().getName();
    }
}
