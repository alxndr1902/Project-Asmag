package com.projectasmag.asmag.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordRequestDTO {
    @NotBlank(message = "Old Password Is Required")
    private String oldPassword;

    @NotBlank(message = "New Password Is Required")
    @Size(max = 200)
    private String newPassword;

    @NotBlank(message = "Confirm Password Is Required")
    @Size(max = 200)
    private String confirmPassword;

    public ChangePasswordRequestDTO() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
