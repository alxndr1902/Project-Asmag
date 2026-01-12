package com.projectasmag.asmag.dto.user;

public class UserResponse {
    private String id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String roleName;

    public UserResponse(String id, String email, String fullName, String phoneNumber, String roleName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRoleName() {
        return roleName;
    }
}
