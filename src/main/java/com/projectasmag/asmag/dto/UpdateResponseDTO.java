package com.projectasmag.asmag.dto;

import java.util.UUID;

public class UpdateResponseDTO {
    private UUID id;
    private Integer version;

    public UpdateResponseDTO(UUID id, Integer version) {
        this.id = id;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }
}
