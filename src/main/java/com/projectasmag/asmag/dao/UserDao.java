package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.company.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao{
    User insert(User user);

    User update(User user);

    List<User> findAll();

    Optional<User> findById(UUID id);

    void deleteById(UUID id);
}
