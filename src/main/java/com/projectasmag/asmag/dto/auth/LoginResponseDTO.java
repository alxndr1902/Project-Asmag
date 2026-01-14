package com.projectasmag.asmag.dto.auth;

public class LoginResponseDTO {
    private final String fullName;
    private final String roleCode;
    private final String token;

    public LoginResponseDTO(String fullName, String roleCode, String token) {
        this.fullName = fullName;
        this.roleCode = roleCode;
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getToken() {
        return token;
    }
}
