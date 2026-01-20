package com.projectasmag.asmag.dto.loan;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CreateLoanRequestDTO {
    @Size(max = 36)
    private String assetTargetId;

    @Size(max = 36)
    private String locationTargetId;

    @Size(max = 36)
    private String employeeTargetId;

    @NotEmpty(message = "Asset To Be Loaned Cannot Be Empty")
    private List<String> assetIdList;

    public String getAssetTargetId() {
        return assetTargetId;
    }

    public void setAssetTargetId(String assetTargetId) {
        this.assetTargetId = assetTargetId;
    }

    public String getLocationTargetId() {
        return locationTargetId;
    }

    public void setLocationTargetId(String locationTargetId) {
        this.locationTargetId = locationTargetId;
    }

    public String getEmployeeTargetId() {
        return employeeTargetId;
    }

    public void setEmployeeTargetId(String employeeTargetId) {
        this.employeeTargetId = employeeTargetId;
    }

    public List<String> getAssetIdList() {
        return assetIdList;
    }

    public void setAssetIdList(List<String> assetIdList) {
        this.assetIdList = assetIdList;
    }
}
