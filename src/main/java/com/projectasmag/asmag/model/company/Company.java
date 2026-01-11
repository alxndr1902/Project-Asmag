package com.projectasmag.asmag.model.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class Company {
    private UUID id;
    private String code;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Employee createdBy;
    private LocalDateTime updatedAt;
    private Employee updatedBy;
    private Integer version;
}
