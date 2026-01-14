package com.projectasmag.asmag.dto.loan;

import java.time.LocalDateTime;

public record LoanDetailResponseDTO(String assetName, LocalDateTime returnDate, Integer version) {
}
