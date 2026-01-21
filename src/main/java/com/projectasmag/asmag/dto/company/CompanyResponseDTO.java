package com.projectasmag.asmag.dto.company;

import java.util.UUID;

public class CompanyResponseDTO {
    private UUID id;
    private String name;
    private String phoneNumber;
    private Integer version;

    public CompanyResponseDTO(UUID id, String name, String phoneNumber, Integer version) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.version = version;
    }

    public CompanyResponseDTO() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getVersion() {
        return version;
    }
}
