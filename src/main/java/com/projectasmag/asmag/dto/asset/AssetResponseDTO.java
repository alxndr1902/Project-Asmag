package com.projectasmag.asmag.dto.asset;

import java.time.LocalDateTime;
import java.util.UUID;

public class AssetResponseDTO {
    private final UUID id;
    private final String code;
    private final String name;
    private final String typeName;
    private final String statusName;
    private final String companyName;
    private final LocalDateTime expiredDate;
    private final LocalDateTime createdAt;
    private final Integer version;

    public AssetResponseDTO(UUID id, String code, String name, String typeName, String statusName, String companyName, LocalDateTime expiredDate, LocalDateTime createdAt, Integer version) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.typeName = typeName;
        this.statusName = statusName;
        this.companyName = companyName;
        this.expiredDate = expiredDate;
        this.createdAt = createdAt;
        this.version = version;
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

    public Integer getVersion() {
        return version;
    }
}
