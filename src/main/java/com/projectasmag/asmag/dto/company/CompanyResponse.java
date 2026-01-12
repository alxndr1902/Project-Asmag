package com.projectasmag.asmag.dto.company;

public class CompanyResponse {
    private String id;
    private String name;
    private String phoneNumber;

    public CompanyResponse(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
