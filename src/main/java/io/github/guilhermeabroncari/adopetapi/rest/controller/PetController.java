package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetResponseDTO;
import io.github.guilhermeabroncari.adopetapi.rest.service.PetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private PetService petService;

    @PostMapping
    public PetResponseDTO create(@RequestBody @Valid PetRequestDTO dto) {
        return petService.create(dto);
    }
    @GetMapping
    public List<Pet> page() {
        return petService.page();
    }
}
