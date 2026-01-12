package com.projectasmag.asmag.model.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class Company {
    private UUID id;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
