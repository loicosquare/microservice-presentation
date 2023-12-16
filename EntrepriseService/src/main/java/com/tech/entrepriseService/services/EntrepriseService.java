package com.tech.entrepriseService.services;

import com.tech.entrepriseService.entities.Entreprise;

import java.util.List;

public interface EntrepriseService {

    //create
    Entreprise create(Entreprise entreprise);

    //get all
    List<Entreprise> getAll();

    //get single
    Entreprise get(String id);
}
