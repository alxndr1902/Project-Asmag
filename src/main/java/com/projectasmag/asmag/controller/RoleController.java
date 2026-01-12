package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.user.RoleResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    @GetMapping()
    public List<RoleResponseDTO> getRoles() {
        return null;
    }

    @GetMapping("{id}")
    public RoleResponseDTO getRole(@PathVariable String id) {
        return null;
    }
}
