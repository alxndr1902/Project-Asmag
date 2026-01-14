package com.projectasmag.asmag.dto.company;

import java.util.UUID;

public class CompanyResponseDTO {
    private UUID id;
    private String name;
    private String phoneNumber;

    public CompanyResponseDTO(UUID id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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
}
