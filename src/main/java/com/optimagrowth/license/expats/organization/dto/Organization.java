package com.optimagrowth.license.expats.organization.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Organization {

    UUID id;

    String name;

    String contactName;

    String contactEmail;

    String contactPhone;
}
