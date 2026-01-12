package com.projectasmag.asmag.dto.loan;

import java.util.List;

public class CreateLoanRequest {
    private String assetTargetId;
    private String locationTargetId;
    private String employeeTargetId;
    private List<String> assetIdList;

    public CreateLoanRequest() {
    }

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
