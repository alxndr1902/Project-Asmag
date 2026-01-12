package com.projectasmag.asmag.dto.location;

public class CreateLocationRequestDTO {
    private String companyId;
    private String name;

    public CreateLocationRequestDTO() {
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
