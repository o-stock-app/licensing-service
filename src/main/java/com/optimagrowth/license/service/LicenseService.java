package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {

    MessageSource messages;
    LicenseRepository licenseRepo;

    public License getLicense(UUID licenseId, UUID organizationId) {
        License license = licenseRepo
                .findByOrganizationIdAndLicenseId(organizationId, licenseId);

        if (null == license) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage(
                                    "license.search.error.message", null, null),
                            licenseId, organizationId));
        }
//        return license.withComment(config.getProperty())
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
}