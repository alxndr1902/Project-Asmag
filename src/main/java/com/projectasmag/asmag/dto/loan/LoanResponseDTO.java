package com.projectasmag.asmag.dto.loan;

import java.util.UUID;

public class LoanResponseDTO {
    private final UUID id;
    private final String code;
    private final String targetName;
    private final Integer version;

    public LoanResponseDTO(UUID id, String code, String targetName, Integer version) {
        this.id = id;
        this.code = code;
        this.targetName = targetName;
        this.version = version;
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

    public Integer getVersion() {
        return version;
    }
}
