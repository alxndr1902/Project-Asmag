package com.projectasmag.asmag.dto.loan;

import java.util.UUID;

public class LoanResponseDTO {
    private final UUID id;
    private final String code;
    private final String targetName;

    public LoanResponseDTO(UUID id, String code, String targetName) {
        this.id = id;
        this.code = code;
        this.targetName = targetName;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTargetName() {
        return targetName;
    }
}
