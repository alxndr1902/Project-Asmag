package com.projectasmag.asmag.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCompanyRequestDTO {
    @NotBlank(message = "Name Is Required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Phone Number Is Required")
    @Size(max = 20)
    private String phoneNumber;

    @NotNull(message = "Please Refresh The Page")
    private Integer version;

    public UpdateCompanyRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
