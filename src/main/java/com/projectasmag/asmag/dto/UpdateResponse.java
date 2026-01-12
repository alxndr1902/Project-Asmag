package com.projectasmag.asmag.dto;

public class UpdateResponse {
    private String id;
    private Integer version;

    public UpdateResponse(String id, Integer version) {
        this.id = id;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }
}
