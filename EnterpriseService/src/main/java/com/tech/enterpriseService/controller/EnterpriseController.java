package com.tech.enterpriseService.controller;

import com.tech.enterpriseService.entities.Enterprise;
import com.tech.enterpriseService.services.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    //create

    @PostMapping
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody Enterprise enterprise) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enterpriseService.create(enterprise));
    }

    //get single
    @GetMapping("/{enterpriseId}")
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable String enterpriseId) {
        return ResponseEntity.status(HttpStatus.OK).body(enterpriseService.get(enterpriseId));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Enterprise>> getAll(){
        return ResponseEntity.ok(enterpriseService.getAll());
    }
}
