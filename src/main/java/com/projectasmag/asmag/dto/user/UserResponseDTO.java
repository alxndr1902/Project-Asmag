package com.projectasmag.asmag.dto.user;

import java.util.UUID;

public record UserResponseDTO(UUID id, String email, String fullName, String phoneNumber, String roleName,
                              Integer version) {
}
