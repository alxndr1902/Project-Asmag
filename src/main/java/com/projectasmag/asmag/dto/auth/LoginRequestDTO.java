package com.projectasmag.asmag.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {
    @NotBlank(message = "Email Is Required")
    @Size(max = 50, message = "Email Maximum Length Is 50 Characters")
    private String email;

    @Size(max = 200, message = "Password Maximum Length Is 200 Characters")
    @NotBlank(message = "Password Is Required")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
