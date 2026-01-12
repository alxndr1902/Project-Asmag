package com.projectasmag.asmag.dto;

public class DeleteResponse {
    private String message;

    public DeleteResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}