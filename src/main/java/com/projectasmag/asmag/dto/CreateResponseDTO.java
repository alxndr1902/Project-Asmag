package com.projectasmag.asmag.dto;

import java.util.UUID;

public class CreateResponseDTO {
    private UUID id;
    private String message;

    public CreateResponseDTO(UUID id, String message) {
        this.id = id;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
