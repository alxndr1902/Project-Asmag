package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.dto.UserRequest;
import com.projectasmag.asmag.dto.UserResponse;
import com.projectasmag.asmag.repository.UserRepository;
import com.projectasmag.asmag.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponse getUser(Integer userId) {
        return null;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }
}
