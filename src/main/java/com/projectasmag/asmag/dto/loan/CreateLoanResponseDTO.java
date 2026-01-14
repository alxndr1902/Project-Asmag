package com.projectasmag.asmag.dto.loan;

import java.util.UUID;

public record CreateLoanResponseDTO(UUID id, String code, String message) {
}
