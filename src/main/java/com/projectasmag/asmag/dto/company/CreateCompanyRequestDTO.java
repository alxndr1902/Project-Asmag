package com.projectasmag.asmag.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCompanyRequestDTO {
    @NotBlank(message = "Name Is Required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Phone Number Is Required")
    @Size(max = 20)
    private String phoneNumber;

    public CreateCompanyRequestDTO() {
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
}
