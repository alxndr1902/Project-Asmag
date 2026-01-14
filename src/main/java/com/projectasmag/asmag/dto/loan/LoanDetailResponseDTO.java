package com.projectasmag.asmag.dto.loan;

import java.time.LocalDateTime;

public class LoanDetailResponseDTO {
    private final String assetName;
    private final LocalDateTime returnDate;
    private final Integer version;

    public LoanDetailResponseDTO(String assetName, LocalDateTime returnDate, Integer version) {
        this.assetName = assetName;
        this.returnDate = returnDate;
        this.version = version;
    }

    public String getAssetName() {
        return assetName;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public Integer getVersion() {
        return version;
    }
}
