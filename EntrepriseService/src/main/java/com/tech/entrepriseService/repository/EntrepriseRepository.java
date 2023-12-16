package com.tech.entrepriseService.repository;

import com.tech.entrepriseService.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
}
