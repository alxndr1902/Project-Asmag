package com.projectasmag.asmag.dto.asset;

import java.util.UUID;

public class AssetStatusResponseDTO {
    private UUID id;
    private String name;

    public AssetStatusResponseDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
