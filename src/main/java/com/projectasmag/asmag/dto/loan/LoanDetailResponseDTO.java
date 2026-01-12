package com.projectasmag.asmag.dto.loan;

public class LoanDetailResponseDTO {
    private String assetName;
    private String returnDate;

    public LoanDetailResponseDTO(String assetName, String returnDate) {
        this.assetName = assetName;
        this.returnDate = returnDate;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
