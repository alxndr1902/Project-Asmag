package com.projectasmag.asmag.dto.loan;

import java.time.LocalDateTime;

public class LoanDetailResponseDTO {
    private String assetName;
    private LocalDateTime returnDate;

    public LoanDetailResponseDTO(String assetName, LocalDateTime returnDate) {
        this.assetName = assetName;
        this.returnDate = returnDate;
    }

    public String getAssetName() {
        return assetName;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }
}
