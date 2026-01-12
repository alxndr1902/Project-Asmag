package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponse;
import com.projectasmag.asmag.dto.auth.LoginRequest;
import com.projectasmag.asmag.dto.auth.LoginResponse;
import com.projectasmag.asmag.dto.auth.RegisterRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @GetMapping("login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return null;
    }

    @PostMapping("register")
    public CreateResponse register(@RequestBody RegisterRequest request) {
        return null;
    }
}
