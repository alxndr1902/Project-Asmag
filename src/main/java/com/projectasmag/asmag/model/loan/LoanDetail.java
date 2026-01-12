package com.projectasmag.asmag.model.loan;

import java.time.LocalDateTime;
import java.util.UUID;

public class LoanDetail {
    private UUID id;
    private String loanId;
    private String assetId;
    private LocalDateTime returnDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer version;
}
