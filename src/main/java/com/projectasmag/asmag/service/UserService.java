package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.user.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
import com.projectasmag.asmag.model.company.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponseDTO> getUsers();

    UserResponseDTO getUser(String id);

    UpdateResponseDTO updateUser(String id, UpdateUserRequestDTO request);

    CreateResponseDTO register(RegisterRequestDTO request);

    UpdateResponseDTO changePassword(ChangePasswordRequestDTO request);

    DeleteResponseDTO deleteUser(String id);

    User findByEmail(String email);
}
