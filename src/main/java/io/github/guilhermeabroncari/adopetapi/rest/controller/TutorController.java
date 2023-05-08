package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorUpdateDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserDeleteRequest;
import io.github.guilhermeabroncari.adopetapi.infra.security.AuthenticationFacade;
import io.github.guilhermeabroncari.adopetapi.rest.service.TutorService;
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
@RequestMapping("/tutors")
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class TutorController {

    private AuthenticationFacade authenticationFacade;
    private TutorService tutorService;

    @PutMapping
    @Secured("ROLE_TUTOR")
    public TutorResponseDTO update(@RequestBody TutorUpdateDTO dto) {
        String email = getEmail();
        return tutorService.update(email, dto);
    }

    @GetMapping
    public Page<TutorResponseDTO> page(@PageableDefault(size = 9) Pageable pageable) {
        return tutorService.page(pageable);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody @Valid UserDeleteRequest deleteRequest) {
        String email = getEmail();
        tutorService.delete(email, deleteRequest);
    }

    private String getEmail() {
        return authenticationFacade.getAuthentication().getName();
    }
}
