package com.projectasmag.asmag.model.asset;

import java.time.LocalDateTime;
import java.util.UUID;

public class Asset {
    private UUID id;
    private String code;
    private String name;
    private String typeId;
    private String statusId;
    private String companyId;
    private LocalDateTime expiredDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
