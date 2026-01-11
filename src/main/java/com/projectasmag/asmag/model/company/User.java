package com.projectasmag.asmag.model.company;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID id;
    private Employee employeeId;
    private String code;
    private String email;
    private String password;
    private Role roleId;
    private LocalDateTime createdAt;
    private Employee createdBy;
    private LocalDateTime updatedAt;
    private Employee updatedBy;
    private Integer version;
}
