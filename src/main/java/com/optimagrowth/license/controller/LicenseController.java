package com.optimagrowth.license.controller;

import com.optimagrowth.license.expats.organization.clients.OrganizationClient;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/optima/v1/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService service;

    @GetMapping("/test")
    public ResponseEntity<?> getLicense() {

        return ResponseEntity.ok("Hello, License service is up and running.");
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense (@PathVariable("licenseId") UUID licenseId) {

        License license = service.getSingleLicense(licenseId);
        return ResponseEntity.ok(license);
    }

    @GetMapping("/{licenseId}/{orgId}")
    public ResponseEntity<License> getLicense (@PathVariable("organizationId") UUID orgId,
                                               @PathVariable("licenseId") UUID licenseId) {

        License license = service.getLicense(licenseId, orgId);
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<?> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request) {

        return ResponseEntity.ok(service.updateLicense(request));
    }

    @PostMapping
    public ResponseEntity<?> createLicense(
            @PathVariable("orgId") String orgId, @RequestBody License request) {

        return ResponseEntity.ok(service.createLicense(request));
    }

    @GetMapping("/bootstrap")
    public ResponseEntity<?> bootstrapLicense() {

        return ResponseEntity.ok(service.getBootstrappedLicenses());
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("orgId") String orgId,
            @PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(service.deleteLicense(UUID.fromString(licenseId)));
    }

}