package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LicenseRepository  extends JpaRepository<License, UUID> {

    public List<License> findByOrganizationId(UUID orgId);

    public License findByOrganizationIdAndLicenseId(UUID orgId, UUID licenseId);
}