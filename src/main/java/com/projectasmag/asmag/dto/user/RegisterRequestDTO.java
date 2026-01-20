
package com.projectasmag.asmag.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {
    @NotBlank(message = "ID Employee Is Required")
    @Size(max = 36)
    private String employeeId;

    @Email(message = "Invalid Email Format")
    @NotBlank(message = "Email Is Required")
    @Size(max = 50, message = "Email Maximum Length Is 50 Characters")
    private String email;

    @NotBlank(message = "Password Is Required")
    @Size(max = 200)
    private String password;

    @NotBlank(message = "Role Is Required")
    @Size(max = 36)
    private String roleId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
