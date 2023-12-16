package com.tech.entrepriseService.controller;

import com.tech.entrepriseService.entities.Entreprise;
import com.tech.entrepriseService.services.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    //create

    @PostMapping
    public ResponseEntity<Entreprise> createHotel(@RequestBody Entreprise entreprise) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entrepriseService.create(entreprise));
    }


    //get single
    @GetMapping("/{entrepriseId}")
    public ResponseEntity<Entreprise> getHotel(@PathVariable String entrepriseId) {
        return ResponseEntity.status(HttpStatus.OK).body(entrepriseService.get(entrepriseId));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Entreprise>> getAll(){
        return ResponseEntity.ok(entrepriseService.getAll());
    }
}
