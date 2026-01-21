package com.projectasmag.asmag.dto;

public class DeleteResponseDTO {
    private String message;

    public DeleteResponseDTO(String message) {
        this.message = message;
    }

    public DeleteResponseDTO() {
    }

    public String getMessage() {
        return message;
    }
}