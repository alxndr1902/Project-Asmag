package com.projectasmag.asmag.dto.asset;

import java.time.LocalDateTime;
import java.util.UUID;

public class AssetResponseDTO {
    private UUID id;
    private String code;
    private String name;
    private String typeName;
    private String statusName;
    private String companyName;
    private LocalDateTime expiredDate;
    private LocalDateTime createdAt;

    public AssetResponseDTO(UUID id, String code, String name, String typeName, String statusName, String companyName, LocalDateTime expiredDate, LocalDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.typeName = typeName;
        this.statusName = statusName;
        this.companyName = companyName;
        this.expiredDate = expiredDate;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
