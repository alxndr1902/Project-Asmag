package com.projectasmag.asmag.dto.auth;

public class LoginResponse {
    private String fullName;
    private String roleCode;
    private String token;

    public LoginResponse(String fullName, String roleCode, String token) {
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
