package com.tech.userService.external.services;

import com.tech.userService.external.entities.Enterprise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ENTERPRISE-SERVICE")
public interface EnterpriseService {

    @GetMapping("/enterprises/{enterpriseId}")
    Enterprise getEnterprise(@PathVariable("enterpriseId") String enterpriseId);
}
