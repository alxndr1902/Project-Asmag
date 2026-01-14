package com.projectasmag.asmag.dto.employee;

import java.util.UUID;

public record EmployeeResponseDTO(UUID id, String fullName, String phoneNumber, String companyName, Integer version) {
}
