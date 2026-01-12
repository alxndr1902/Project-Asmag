package com.projectasmag.asmag.dto;

public class UpdateResponseDTO {
    private String id;
    private Integer version;

    public UpdateResponseDTO(String id, Integer version) {
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
