package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.auth.LoginRequestDTO;
import com.projectasmag.asmag.dto.auth.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @PostMapping("login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO request) {
        return null;
    }


}
