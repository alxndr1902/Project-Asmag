package com.projectasmag.asmag.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserRequestDTO {
    @NotBlank(message = "Email Is Required")
    @Size(max = 50, message = "Email Maximum Length Is 50 Characters")
    private String email;

    @NotBlank(message = "Please Refresh The Page")
    @NotNull
    private Integer version;

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
