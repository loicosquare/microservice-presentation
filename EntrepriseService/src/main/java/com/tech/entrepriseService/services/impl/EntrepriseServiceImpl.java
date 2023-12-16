package com.tech.entrepriseService.services.impl;

import com.tech.entrepriseService.entities.Entreprise;
import com.tech.entrepriseService.exception.ResourceNotFoundException;
import com.tech.entrepriseService.repository.EntrepriseRepository;
import com.tech.entrepriseService.services.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    /**
     * @param entreprise
     * @return
     */
    @Override
    public Entreprise create(Entreprise entreprise) {
        String entrepriseId = UUID.randomUUID().toString();

        return  entrepriseRepository.save(Entreprise.builder()
                .id(entrepriseId)
                .name(entreprise.getName())
                .location(entreprise.getLocation())
                .about(entreprise.getAbout())
                .build()
        );
    }

    /**
     * @return
     */
    @Override
    public List<Entreprise> getAll() {
        return entrepriseRepository.findAll().stream().toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Entreprise get(String id) {
        return entrepriseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entreprise with id : {} not found !!" + id));
    }
}
