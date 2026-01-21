package com.projectasmag.asmag.dto.employee;

import java.util.UUID;

public class EmployeeResponseDTO {
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String companyName;
    private Integer version;

    public EmployeeResponseDTO(UUID id, String fullName, String phoneNumber, String companyName, Integer version) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.version = version;
    }

    public EmployeeResponseDTO() {
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
