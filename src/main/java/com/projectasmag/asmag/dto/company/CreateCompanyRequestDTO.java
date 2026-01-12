package com.projectasmag.asmag.dto.company;

public class CreateCompanyRequestDTO {
    private String name;
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
