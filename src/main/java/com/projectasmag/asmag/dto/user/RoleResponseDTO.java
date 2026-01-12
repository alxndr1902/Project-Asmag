package com.projectasmag.asmag.dto.user;

public class RoleResponseDTO {
    private String id;
    private String name;

    public RoleResponseDTO(String id, String name) {
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
