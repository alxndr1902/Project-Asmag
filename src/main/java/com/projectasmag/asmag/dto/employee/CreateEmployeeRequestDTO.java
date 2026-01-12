package com.projectasmag.asmag.dto.employee;

public class CreateEmployeeRequestDTO {
    private String fullName;
    private String companyId;
    private String phoneNumber;
    private String identificationNumber;


    public CreateEmployeeRequestDTO(String fullName, String companyId, String phoneNumber, String identificationNumber) {
        this.fullName = fullName;
        this.companyId = companyId;
        this.phoneNumber = phoneNumber;
        this.identificationNumber = identificationNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }
}
