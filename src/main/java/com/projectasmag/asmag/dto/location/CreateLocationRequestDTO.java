package com.projectasmag.asmag.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateLocationRequestDTO {
    @NotBlank(message = "Company Is Required")
    @Size(max = 36)
    private String companyId;

    @NotBlank(message = "Name Is Required")
    @Size(max = 100, message = "Name Maximum Length Is 100 Characters")
    private String name;

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
