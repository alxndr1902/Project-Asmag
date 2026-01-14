package com.projectasmag.asmag.dto.asset;

import java.time.LocalDateTime;
import java.util.UUID;

public record AssetResponseDTO(UUID id, String code, String name,
                               String typeName, String statusName, String companyName,
                               LocalDateTime expiredDate, LocalDateTime createdAt, Integer version) {
}


