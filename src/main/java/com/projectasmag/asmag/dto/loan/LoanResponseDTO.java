package com.projectasmag.asmag.dto.loan;

public class LoanResponseDTO {
    private String id;
    private String code;
    private String targetName;

    public LoanResponseDTO(String id, String code, String targetName) {
        this.id = id;
        this.code = code;
        this.targetName = targetName;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTargetName() {
        return targetName;
    }
}
