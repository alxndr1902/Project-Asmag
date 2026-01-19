package com.projectasmag.asmag.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAssetRequestDTO {
    @NotBlank(message = "Code Is Required")
    @Size(max = 10)
    private String code;

    @NotBlank(message = "Name Is Required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Asset Type Is Required")
    @Size(max = 36)
    private String typeId;

    @NotBlank(message = "Asset Status Is Required")
    @Size(max = 36)
    private String statusId;

    @NotBlank(message = "Asset Company Is Required")
    @Size(max = 36)
    private String companyId;
    @Size(max = 36)
    private String expiredDate;

    public CreateAssetRequestDTO() {
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
