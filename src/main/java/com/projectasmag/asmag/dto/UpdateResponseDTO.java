package com.projectasmag.asmag.dto;

public class UpdateResponseDTO {
    private Integer version;
    private String message;

    public UpdateResponseDTO(Integer version, String message) {
        this.version = version;
        this.message = message;
    }

    public UpdateResponseDTO() {
    }

    public Integer getVersion() {
        return version;
    }

    public String getMessage() {
        return message;
    }
}
