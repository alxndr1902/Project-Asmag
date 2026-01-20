package com.projectasmag.asmag.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateEmployeeRequestDTO {
    @NotBlank(message = "Full Name Is Required")
    @Size(max = 50, message = "Full Name Maximum Length Is 50 Characters")
    private String fullName;

    @NotBlank(message = "Phone Number Is Required")
    @Size(max = 20, message = "Phone Number Maximum Length Is 20 Characters")
    private String phoneNumber;

    @NotNull(message = "Please Refresh The Page")
    private Integer version;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
