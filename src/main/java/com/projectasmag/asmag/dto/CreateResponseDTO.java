package com.projectasmag.asmag.dto;

public class CreateResponseDTO {
    private String id;
    private String message;

    public CreateResponseDTO(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
