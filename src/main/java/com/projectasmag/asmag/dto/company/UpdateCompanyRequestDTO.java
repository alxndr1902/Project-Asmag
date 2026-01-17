package com.projectasmag.asmag.dto.company;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCompanyRequestDTO {
    @Size(max = 100)
    private String name;

    @Size(max = 20)
    private String phoneNumber;

    @NotNull
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
