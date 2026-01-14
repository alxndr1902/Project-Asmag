package com.projectasmag.asmag.dto;

public class DeleteResponseDTO {
    private final String message;

    public DeleteResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}