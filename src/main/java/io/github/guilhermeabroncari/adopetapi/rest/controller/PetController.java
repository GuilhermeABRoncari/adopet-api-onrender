package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetUpdateDTO;
import io.github.guilhermeabroncari.adopetapi.infra.security.AuthenticationFacade;
import io.github.guilhermeabroncari.adopetapi.rest.service.PetService;
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
@RequestMapping("/pets")
@EnableMethodSecurity(securedEnabled = true)
@Secured("ROLE_SHELTER")
@AllArgsConstructor
//@SecurityRequirement(name = "bearer-key")
public class PetController {

    private PetService petService;
    private AuthenticationFacade authenticationFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponseDTO create(@RequestBody @Valid PetRequestDTO dto) {
        String email = getEmail();
        return petService.create(email, dto);
    }

    @GetMapping
    public Page<PetResponseDTO> page(@PageableDefault(size = 9) Pageable pageable) {
        String email = getEmail();
        return petService.page(email, pageable);
    }
    @GetMapping("{id}")
    public PetResponseDTO find(@PathVariable Long id) {
        String email = getEmail();
        return petService.find(email, id);
    }
    @PutMapping
    public PetResponseDTO update(@PathVariable @Valid PetUpdateDTO petUpdateDTO) {
        String email = getEmail();
        return petService.update(email, petUpdateDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        String email = getEmail();
        petService.delete(email, id);
    }

    private String getEmail() {
        return authenticationFacade.getAuthentication().getName();
    }
}
