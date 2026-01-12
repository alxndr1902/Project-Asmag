package com.projectasmag.asmag.model.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class Role {
    private UUID id;
    private String code;
    private String name;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
