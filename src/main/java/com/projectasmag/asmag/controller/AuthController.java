package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.auth.LoginRequestDTO;
import com.projectasmag.asmag.dto.auth.LoginResponseDTO;
import com.projectasmag.asmag.dto.auth.RegisterRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @GetMapping("login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return null;
    }

    @PostMapping("register")
    public CreateResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return null;
    }
}
