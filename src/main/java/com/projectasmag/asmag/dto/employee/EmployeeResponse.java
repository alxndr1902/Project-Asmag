package com.projectasmag.asmag.dto.employee;

public class EmployeeResponse {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String companyName;

    public EmployeeResponse(String id, String fullName, String phoneNumber, String companyName) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }

    public String getId() {
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
}
