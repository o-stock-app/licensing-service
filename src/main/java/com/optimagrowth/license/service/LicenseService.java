package com.optimagrowth.license.service;

import com.optimagrowth.license.expats.organization.clients.OrganizationClient;
import com.optimagrowth.license.expats.organization.dto.Organization;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messages;
    private final LicenseRepository licenseRepo;
    private final OrganizationClient orgClient;

    private static final Logger log = LoggerFactory.getLogger(LicenseService.class);

    public License getSingleLicense(UUID licenseId) {
        Optional<License> license = licenseRepo.findById(licenseId);

        if (null == license.get()) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage(
                                    "license.search.error.message", null, null),
                            licenseId));
        }
        return license.get();
    }

    public License getLicense(UUID licenseId, UUID organizationId) {
        License license = licenseRepo
                .findByOrganizationIdAndLicenseId(organizationId, licenseId);

        if (null == license) {
            throw new IllegalArgumentException(
                String.format(messages.getMessage(
                "license.search.error.message", null, null),
                        licenseId, organizationId));
        }
        return license;
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID());
        return licenseRepo.save(license);
    }

    public License updateLicense(License license) {
        return licenseRepo.save(license);
    }

    public String deleteLicense(UUID licenseId) {
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepo.delete(license);
        responseMessage = String.format(messages.getMessage(
                "license.delete.message", null, null),licenseId);
        return responseMessage;
    }

    @CircuitBreaker(name = "organization-service", fallbackMethod = "getAllOrgsFallback")
    public List<License> getBootstrappedLicenses() {

        List<License> licenses = new ArrayList<>();

        List<Organization> orgs = orgClient.getAllOrganizations();

        orgs.forEach(org -> {
            for (int i = 1; i <= 10; i++) {
                License license = new License();
                license.setDescription("License Desc for License " + i);
                license.setOrganizationId(org.getId());
                license.setProductName("Product " + i);
                license.setLicenseType("Type " + i);
                license.setComment("Dummy license " + i + " for " + org.getName());

                licenses.add(licenseRepo.save(license));
            }
        });
        return licenses;
    }

    public List<License> getAllOrgsFallback(Throwable t) {
        log.warn("Fallback triggered in getBootstrappedLicenses due to: {}", t.getMessage());
        return Collections.emptyList();
    }
}