package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.auth.LoginRequestDTO;
import com.projectasmag.asmag.dto.auth.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
