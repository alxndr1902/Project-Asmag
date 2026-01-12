package com.projectasmag.asmag.model.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID id;
    private String employeeId;
    private String email;
    private String password;
    private String roleId;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
