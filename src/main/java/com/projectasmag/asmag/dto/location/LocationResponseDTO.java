package com.projectasmag.asmag.dto.location;

import java.util.UUID;

public record LocationResponseDTO(UUID id, String name, String companyName, Integer version) {
}
