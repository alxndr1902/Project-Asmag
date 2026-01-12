package com.projectasmag.asmag.model.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class Employee {
    private UUID id;
    private String companyId;
    private String fullName;
    private String phoneNumber;
    private String identificationNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
