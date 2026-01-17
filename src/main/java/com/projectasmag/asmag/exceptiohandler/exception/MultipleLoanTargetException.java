package com.projectasmag.asmag.exceptiohandler.exception;

import java.util.UUID;

public class MultipleLoanTargetException extends RuntimeException {
    private final UUID assetTargetID;
    private final UUID locationTargetID;
    private final UUID employeTargetID;

    public MultipleLoanTargetException(String message, UUID assetTargetID, UUID locationTargetID, UUID employeTargetID) {
        super(message);
        this.assetTargetID = assetTargetID;
        this.locationTargetID = locationTargetID;
        this.employeTargetID = employeTargetID;
    }

    public UUID getAssetTargetID() {
        return assetTargetID;
    }

    public UUID getLocationTargetID() {
        return locationTargetID;
    }

    public UUID getEmployeTargetID() {
        return employeTargetID;
    }
}
