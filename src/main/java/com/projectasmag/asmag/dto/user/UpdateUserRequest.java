package com.projectasmag.asmag.dto.user;

public class UpdateUserRequest {
    private String email;
    private Integer version;

    public UpdateUserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
