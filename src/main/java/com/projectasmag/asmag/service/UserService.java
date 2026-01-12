package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.auth.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getUsers();

    UserResponseDTO getUser(String id);

    UpdateResponseDTO updateUser(String id, UpdateUserRequestDTO request);

    CreateResponseDTO register(RegisterRequestDTO request);

    UpdateResponseDTO changePassword(ChangePasswordRequestDTO request);

    DeleteResponseDTO deleteUser(String id);
}
