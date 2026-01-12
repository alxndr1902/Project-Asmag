package com.projectasmag.asmag.model.loan;

import java.time.LocalDateTime;
import java.util.UUID;

public class Loan {
    private UUID id;
    private String code;
    private LocalDateTime loanDate;
    private String assetTargetId;
    private String locationTargetId;
    private String employeeTargetId;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
