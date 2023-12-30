package com.tech.enterpriseService.services.impl;

import com.tech.enterpriseService.entities.Enterprise;
import com.tech.enterpriseService.exception.ResourceNotFoundException;
import com.tech.enterpriseService.repository.EnterpriseRepository;
import com.tech.enterpriseService.services.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    /**
     * @param enterprise
     * @return
     */
    @Override
    public Enterprise create(Enterprise enterprise) {
        String enterpriseId = UUID.randomUUID().toString();

        return  enterpriseRepository.save(Enterprise.builder()
                .id(enterpriseId)
                .name(enterprise.getName())
                .location(enterprise.getLocation())
                .about(enterprise.getAbout())
                .build()
        );
    }

    /**
     * @return
     */
    @Override
    public List<Enterprise> getAll() {
        return enterpriseRepository.findAll().stream().toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Enterprise get(String id) {
        return enterpriseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enterprise with id : {} not found !!" + id));
    }
}
