package com.projectasmag.asmag.dto.asset;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateAssetRequestDTO {
    @Size(max = 10)
    private String code;

    @Size(max = 100)
    private String name;

    @NotNull
    private Integer version;

    public UpdateAssetRequestDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
