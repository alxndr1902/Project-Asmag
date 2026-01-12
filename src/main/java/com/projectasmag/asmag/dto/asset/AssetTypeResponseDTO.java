package com.projectasmag.asmag.dto.asset;

public class AssetTypeResponseDTO {
    private String id;
    private String name;

    public AssetTypeResponseDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
