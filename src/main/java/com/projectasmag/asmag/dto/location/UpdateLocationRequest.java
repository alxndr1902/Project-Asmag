package com.projectasmag.asmag.dto.location;

public class UpdateLocationRequest {
    private String companyId;
    private String name;
    private Integer version;

    public UpdateLocationRequest(String companyId, String name, Integer version) {
        this.companyId = companyId;
        this.name = name;
        this.version = version;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
