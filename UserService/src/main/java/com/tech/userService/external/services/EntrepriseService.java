package com.tech.userService.external.services;

import com.tech.userService.entities.externalEntities.Entreprise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ENTREPRISE-SERVICE")
public interface EntrepriseService {

    @GetMapping("/entreprises/{entrepriseId}")
    Entreprise getHotel(@PathVariable("entrepriseId") String hotelId);
}
