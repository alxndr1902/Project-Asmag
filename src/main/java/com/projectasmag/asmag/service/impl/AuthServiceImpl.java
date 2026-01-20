package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.dto.auth.LoginRequestDTO;
import com.projectasmag.asmag.dto.auth.LoginResponseDTO;
import com.projectasmag.asmag.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
