package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.user.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
import com.projectasmag.asmag.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> response = userService.getUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String id) {
        UserResponseDTO response = userService.getUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDTO> updateUser(@PathVariable String id,
                                        @RequestBody @Valid UpdateUserRequestDTO updateUserRequest) {
        UpdateResponseDTO response = userService.updateUser(id, updateUserRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<CreateResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        CreateResponseDTO response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDTO> deleteUser(@PathVariable String id) {
        DeleteResponseDTO response = userService.deleteUser(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("change-password")
    public UpdateResponseDTO changePassword(@RequestBody @Valid ChangePasswordRequestDTO changePasswordRequest) {
        return null;
    }
}
