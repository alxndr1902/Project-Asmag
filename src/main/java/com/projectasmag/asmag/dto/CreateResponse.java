package com.projectasmag.asmag.dto;

public class CreateResponse {
    private String id;
    private String message;

    public CreateResponse(String id, String message) {
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
