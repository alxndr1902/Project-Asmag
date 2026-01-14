package com.projectasmag.asmag.dto.loan;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoanDetailResponseDTO(UUID id, String assetName, LocalDateTime returnDate, Integer version) {
}
