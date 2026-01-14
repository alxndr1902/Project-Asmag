package com.projectasmag.asmag.dto.role;

import java.util.UUID;

public class RoleResponseDTO {
    private final UUID id;
    private final String name;

    public RoleResponseDTO(UUID id, String name) {
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
