package com.projectasmag.asmag.dto.asset;

import java.util.UUID;

public class AssetTypeResponseDTO {
    private final UUID id;
    private final String name;

    public AssetTypeResponseDTO(UUID id, String name) {
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
