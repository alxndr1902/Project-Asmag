package com.projectasmag.asmag.dto.user;

public class RoleResponse {
    private String id;
    private String name;

    public RoleResponse(String id, String name) {
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
