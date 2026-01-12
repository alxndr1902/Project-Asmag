package com.projectasmag.asmag.dto.loan;

public class LoanDetailResponse {
    private String assetName;
    private String returnDate;

    public LoanDetailResponse(String assetName, String returnDate) {
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
