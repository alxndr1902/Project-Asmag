package com.projectasmag.asmag.dto.loan;

import java.util.UUID;

public class CreateLoanResponseDTO {
    private final UUID id;
    private final String code;
    private final String message;

    public CreateLoanResponseDTO(UUID id, String code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
