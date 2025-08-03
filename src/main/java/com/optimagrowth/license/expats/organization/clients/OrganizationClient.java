package com.optimagrowth.license.expats.organization.clients;

import com.optimagrowth.license.expats.organization.dto.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "organization-service")
public interface OrganizationClient {

    @GetMapping("/v1/organization/{organizationId}")
    Organization getOrganization(@PathVariable("organizationId")UUID organizationId);

    @GetMapping("/v1/organization/bootstrap")
    List<Organization> getAllOrganizations();
}