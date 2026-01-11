package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.UserRequest;
import com.projectasmag.asmag.dto.UserResponse;

public interface UserService {
    UserResponse getUser(Integer userId);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Integer id, UserRequest userRequest);

    void deleteUser(Integer id);
}
