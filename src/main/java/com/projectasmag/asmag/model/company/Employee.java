package com.projectasmag.asmag.model.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class Employee {
    private UUID id;
    private Company companyId;
    private String code;
    private String fullName;
    private String phoneNumber;
    private String identification_number;
    private LocalDateTime createdAt;
    private Employee createdBy;
    private LocalDateTime updatedAt;
    private Employee updatedBy;
    private Integer version;
}
