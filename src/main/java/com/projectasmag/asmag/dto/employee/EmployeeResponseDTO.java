package com.projectasmag.asmag.dto.employee;

import java.util.UUID;

public class EmployeeResponseDTO {
    private final UUID id;
    private final String fullName;
    private final String phoneNumber;
    private final String companyName;
    private final Integer version;

    public EmployeeResponseDTO(UUID id, String fullName, String phoneNumber, String companyName, Integer version) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getVersion() {
        return version;
    }
}
