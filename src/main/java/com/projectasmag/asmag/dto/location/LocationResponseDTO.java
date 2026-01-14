package com.projectasmag.asmag.dto.location;

import java.util.UUID;

public class LocationResponseDTO {
    private final UUID id;
    private final String name;
    private final String companyName;

    public LocationResponseDTO(UUID id, String name, String companyName) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }
}
