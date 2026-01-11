package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.UserRequest;
import com.projectasmag.asmag.dto.UserResponse;
import com.projectasmag.asmag.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    private UserResponse getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    private UserResponse updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
