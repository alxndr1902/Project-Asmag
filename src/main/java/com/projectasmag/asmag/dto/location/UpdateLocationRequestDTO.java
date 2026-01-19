package com.projectasmag.asmag.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateLocationRequestDTO {
    @NotBlank(message = "Name Is Required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Please Refresh The Page")
    @NotNull
    private Integer version;

    public UpdateLocationRequestDTO(String name, Integer version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
