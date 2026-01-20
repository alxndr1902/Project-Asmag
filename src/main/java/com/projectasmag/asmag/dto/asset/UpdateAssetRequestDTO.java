package com.projectasmag.asmag.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateAssetRequestDTO {
    @NotBlank(message = "Code Is Required")
    @Size(max = 10, message = "Code Maximum Length Is 10 Characters")
    private String code;

    @NotBlank(message = "Name Is Required")
    @Size(max = 100, message = "Name Maximum Length Is 100 Characters")
    private String name;

    @NotNull(message = "Please Refresh The Page")
    private Integer version;

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
