package com.projectasmag.asmag.model.asset;

import java.time.LocalDateTime;
import java.util.UUID;

public class AssetType {
    private UUID id;
    private String name;
    private String code;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
