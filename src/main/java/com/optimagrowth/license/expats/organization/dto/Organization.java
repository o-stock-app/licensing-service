package com.optimagrowth.license.expats.organization.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    UUID id;

    String name;

    String contactName;

    String contactEmail;

    String contactPhone;
}