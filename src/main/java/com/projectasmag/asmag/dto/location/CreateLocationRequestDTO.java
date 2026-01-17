package com.projectasmag.asmag.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateLocationRequestDTO {
    @NotBlank
    @Size(max = 36)
    private String companyId;

    @NotBlank
    @Size(max = 100)
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
