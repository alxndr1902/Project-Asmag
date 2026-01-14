package com.projectasmag.asmag.dto.location;

public class UpdateLocationRequestDTO {
    private String name;
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
