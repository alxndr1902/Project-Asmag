package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.DeleteResponse;
import com.projectasmag.asmag.dto.UpdateResponse;
import com.projectasmag.asmag.dto.user.ChangePasswordRequest;
import com.projectasmag.asmag.dto.user.RoleResponse;
import com.projectasmag.asmag.dto.user.UpdateUserRequest;
import com.projectasmag.asmag.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public List<UserResponse> getUsers() {
        return null;
    }

    @GetMapping("{id}")
    public UserResponse getUser(@PathVariable String id) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponse updateUser(@PathVariable String id,
                                     @RequestBody UpdateUserRequest updateUserRequest) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponse deleteUser(@PathVariable String id) {
        return null;
    }

    @PatchMapping("change-password")
    public UpdateResponse changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    @GetMapping("roles")
    public List<RoleResponse> getRoles() {
        return null;
    }

    @GetMapping("roles/{id}")
    public RoleResponse getRole(@PathVariable String id) {
        return null;
    }
}
