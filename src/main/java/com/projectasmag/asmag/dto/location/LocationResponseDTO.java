package com.projectasmag.asmag.dto.location;

import java.util.UUID;

public class LocationResponseDTO {
    private final UUID id;
    private final String name;
    private final String companyName;
    private final Integer version;

    public LocationResponseDTO(UUID id, String name, String companyName, Integer version) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.version = version;
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

    public Integer getVersion() {
        return version;
    }
}
