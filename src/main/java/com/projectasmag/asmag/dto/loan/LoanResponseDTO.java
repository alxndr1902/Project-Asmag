package com.projectasmag.asmag.dto.loan;

import java.util.UUID;

public record LoanResponseDTO(UUID id, String code, String targetName, Integer version) {
}
