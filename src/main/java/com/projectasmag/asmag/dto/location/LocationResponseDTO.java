package com.projectasmag.asmag.dto.location;

public class LocationResponseDTO {
    private String id;
    private String name;
    private String companyName;

    public LocationResponseDTO(String id, String name, String companyName) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }
}
