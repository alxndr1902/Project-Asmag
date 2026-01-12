package com.projectasmag.asmag.dto.company;

public class CreateCompanyRequest {
    private String name;
    private String phoneNumber;

    public CreateCompanyRequest() {
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
