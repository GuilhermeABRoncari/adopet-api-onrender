package io.github.guilhermeabroncari.adopetapi.rest.service;

import io.github.guilhermeabroncari.adopetapi.domain.entity.adopetmessage.*;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.PetRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.Shelter;
import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.Tutor;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdopetMessageService {

    private AdopetMessageRepository adopetMessageRepository;
    private TutorRepository tutorRepository;
    private ShelterRepository shelterRepository;
    private PetRepository petRepository;
    private static final String ENTITY_NOT_FOUND = "Entity id not found for: ";

    @Transactional
    public AdopetMessageResponseDTO sendMessage(String email, AdopetMessageRequestDTO dto) {
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        Shelter shelter = shelterRepository.findById(dto.shelterId()).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "shelter"));
        Pet pet = petRepository.findByNameLike("%" + dto.petName() + "%");
        if (pet == null) throw new IllegalArgumentException("Pet whit name like: " + dto.petName() + " not found.");

        if (!shelter.getPetList().contains(pet))
            throw new IllegalArgumentException(ENTITY_NOT_FOUND + "pet in this shelter.");
        var message = new AdopetMessage(null, dto.message(), OffsetDateTime.now(), tutor, shelter, pet);
        adopetMessageRepository.save(message);
        tutor.getMessageList().add(message);
        shelter.getMessageList().add(message);

        return new AdopetMessageResponseDTO(message);
    }

    public Page<AdopetMessageResponseDTO> pageMessages(String email, Pageable pageable) {
        List<AdopetMessage> messageList = new ArrayList<>();
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        if (tutor != null) {
            messageList.addAll(tutor.getMessageList());
        } else {
            Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
            messageList.addAll(shelter.getMessageList());
        }
        // Classifique a lista por data
        messageList.sort(Comparator.comparing(AdopetMessage::getDateTime).reversed());
        // Crie uma nova página com base na lista classificada
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<AdopetMessageResponseDTO> messageResponseList = messageList.stream()
                .skip(startItem)
                .limit(pageSize)
                .map(AdopetMessageResponseDTO::new)
                .collect(Collectors.toList());
        return new PageImpl<>(messageResponseList, PageRequest.of(currentPage, pageSize), messageList.size());
    }

    public AdopetMessageResponseDTO getMessageById(String email, Long id) {
        AdopetMessage message = adopetMessageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "message"));
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        if (tutor != null) {
            if (tutor.getMessageList().contains(message)) return new AdopetMessageResponseDTO(message);
        } else {
            Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
            if (shelter.getMessageList().contains(message)) return new AdopetMessageResponseDTO(message);
        }
        throw new IllegalArgumentException(ENTITY_NOT_FOUND + "message");
    }

    @Transactional
    public AdopetMessageResponseDTO updateMessage(String email, AdopetMessageUpdateDTO dto) {
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        var message = adopetMessageRepository.findById(dto.messageId()).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "message"));
        if (!tutor.getMessageList().contains(message)) throw new IllegalArgumentException(ENTITY_NOT_FOUND + "message");

        message.update(dto);
        adopetMessageRepository.save(message);
        return new AdopetMessageResponseDTO(message);
    }

    @Transactional
    public void deleteMessage(String email, Long id) {
        var message = adopetMessageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ENTITY_NOT_FOUND + "message"));
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        if (tutor != null && tutor.getMessageList().contains(message)) {
            adopetMessageRepository.delete(message);
        } else {
            Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
            if (shelter.getMessageList().contains(message)) adopetMessageRepository.delete(message);
        }
    }
}
