package com.tech.enterpriseService.services;

import com.tech.enterpriseService.entities.Enterprise;

import java.util.List;

public interface EnterpriseService {

    //create
    Enterprise create(Enterprise enterprise);

    //get all
    List<Enterprise> getAll();

    //get single
    Enterprise get(String id);
}
