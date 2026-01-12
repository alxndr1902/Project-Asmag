package com.projectasmag.asmag.dto.asset;

public class AssetStatusResponse {
    private String id;
    private String name;

    public AssetStatusResponse(String id, String name) {
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
