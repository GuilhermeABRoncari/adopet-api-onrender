package io.github.guilhermeabroncari.adopetapi.rest.controller;

import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping
    public Page<PetResponseDTO> randomPage(@PageableDefault(size = 9, sort = "id") Pageable pageable) {
        Random random = new Random();
        long count = petRepository.countByAdoptedFalse();
        int randomIndex = random.nextInt((int) count);
        PageRequest randomPageRequest = PageRequest.of(randomIndex / pageable.getPageSize(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));
        return petRepository.findAllByAdoptedFalse(randomPageRequest).map(PetResponseDTO::new);
    }
}
