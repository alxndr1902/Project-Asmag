package com.projectasmag.asmag.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateEmployeeRequestDTO {
    @NotBlank(message = "Full Name Is Required")
    @Size(max = 50, message = "Full Name Maximum Length Is 50 Characters")
    private String fullName;

    @NotBlank(message = "Company Is Required")
    @Size(max = 36)
    private String companyId;

    @NotBlank(message = "Phone Number Is Required")
    @Size(max = 20, message = "Phone Number Maximum Length Is 20 Characters")
    private String phoneNumber;

    @NotBlank(message = "Identification Number Is Required")
    @Size(max = 20, message = "Identification Number Maximum Length Is 20 Characters")
    private String identificationNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
