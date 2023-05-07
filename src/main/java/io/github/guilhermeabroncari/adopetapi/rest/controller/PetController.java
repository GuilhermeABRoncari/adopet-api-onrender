package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRequestDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetResponseDTO;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetUpdateDTO;
import io.github.guilhermeabroncari.adopetapi.rest.service.PetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponseDTO create(@RequestBody @Valid PetRequestDTO dto) {
        return petService.create(dto);
    }

    @GetMapping
    public Page<PetResponseDTO> page(@PageableDefault(size = 9) Pageable pageable) {
        return petService.page(pageable);
    }
    @GetMapping("{id}")
    public PetResponseDTO find(@PathVariable Long id) {
        return petService.find(id);
    }
    @PutMapping
    public PetResponseDTO update(@PathVariable @Valid PetUpdateDTO petUpdateDTO) {
        return petService.update(petUpdateDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        petService.delete(id);
    }
}
