package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.auth.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return null;
    }

    @GetMapping("{id}")
    public UserResponseDTO getUser(@PathVariable String id) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponseDTO updateUser(@PathVariable String id,
                                        @RequestBody UpdateUserRequestDTO updateUserRequest) {
        return null;
    }

    @PostMapping("register")
    public CreateResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponseDTO deleteUser(@PathVariable String id) {
        return null;
    }

    @PatchMapping("change-password")
    public UpdateResponseDTO changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequest) {
        return null;
    }
}
