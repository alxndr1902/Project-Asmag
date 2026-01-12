package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.company.CompanyResponseDTO;
import com.projectasmag.asmag.dto.company.CreateCompanyRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    @GetMapping
    public List<CompanyResponseDTO> getCompanies() {
        return null;
    }

    @GetMapping("{id}")
    public CompanyResponseDTO getCompany(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponseDTO createCompany(@RequestBody CreateCompanyRequestDTO request) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponseDTO updateCompany(@PathVariable String id,
                                           @RequestBody CreateCompanyRequestDTO request) {
        return null;
    }
}
